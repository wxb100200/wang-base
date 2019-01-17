package com.base.wang.controller;

import com.base.wang.common.MyException;
import com.base.wang.common.Office2PDF;
import com.base.wang.common.PageReturn;
import com.base.wang.entity.BasAttachment;
import com.base.wang.service.BasAttachmentService;
import com.base.wang.util.DateUtil;
import com.base.wang.util.DigestUtils;
import com.base.wang.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by wxb on 2018/12/28.
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController {
    @Value("${upload.path}")
    private String uploadPath;

    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    BasAttachmentService attachmentService;
    /**
     * 上传单个附件文件
     */
    @ResponseBody
    @RequestMapping(value = "/uploadOne", method = RequestMethod.POST)
    public Object uploadOne( @RequestParam("file") MultipartFile partFile, HttpServletRequest request) {
        //根据当前时间获得一个路径
        StringBuilder path=new StringBuilder();
        Date data=new Date();
        path.append("//").append(DateUtil.format(data, "yyyy"));
        path.append("//").append(DateUtil.format(data, "MM"));
        path.append("//").append(DateUtil.format(data, "dd"));
        String time=DateUtil.format(data,"yyyyMMdd");
        path.append("//").append(time);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        path.append(uuid);
        File file= new File(uploadPath+path);
        try {
            InputStream inputStream = partFile.getInputStream();//获取文件流
            FileUtils.copyInputStreamToFile(inputStream, file);//将文件拷贝到指定目录下
        } catch (IOException e) {
            e.printStackTrace();
            return PageReturn.fail("获取文件流异常");
        }
        String fileName = partFile.getOriginalFilename();//上传的文件名称
        BasAttachment attachment=new BasAttachment();
        attachment.setName(fileName);
        attachment.setFileName(time+uuid);
        attachment.setFilePath(path.toString());
        attachment.setFileSize(file.length());
        attachment.setCreateTime(new Date());
        attachmentService.save(attachment);
        return PageReturn.successData(attachment);
    }
    /**
     * 下载单个文件
     */
    @ResponseBody
    @RequestMapping(value = "/downLoad/{fileName}")
    public void downSingle(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "fileName") String fileName)  {
        if(StringUtil.isEmpty(fileName))throw new MyException("文件名字不能为空");
        BasAttachment attachment=attachmentService.findByFileName(fileName);
        if(attachment==null) throw new MyException("文件名称错误");
        String name=attachment.getName();
        String filePath=attachment.getFilePath();
        File file=new File(uploadPath+filePath);
        try {
            String userAgent = request.getHeader("user-agent").toLowerCase();
            String downloadFileName;
            if (userAgent.contains("msie") || userAgent.contains("like gecko") ) {
                // win10 ie edge 浏览器 和其他系统的ie
                downloadFileName = URLEncoder.encode(name, "UTF-8");
            } else {
                // fe
                downloadFileName = new String(name.getBytes("UTF-8"), "iso-8859-1");
            }
            response.addHeader("content-disposition", "attachment;filename="+downloadFileName);
            FileUtils.copyFile(file, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("下载附件异常");
        }
    }
    /**
     * 在浏览器里显示文件
     */
    @RequestMapping(value = "/viewFile/{fileName}",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public void viewFile(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "fileName") String fileName){
        if(StringUtil.isEmpty(fileName))throw new MyException("文件名字不能为空");
        BasAttachment attachment=attachmentService.findByFileName(fileName);
        if(attachment==null) throw new MyException("文件名称错误");
        String name=attachment.getName();//文件名称
        String extensionName="";//扩展名
        String filePath=attachment.getFilePath();//文件存放路径
        if(name.contains(".")) {
            extensionName = name.substring(name.lastIndexOf(".")+1);
        }
        String path = uploadPath+filePath;//网络图片地址
        File file=null;

        String[] imageType={"jpg","jpeg","png","gif"};
        String[] officeType={"doc","docx","xls","xlsx","ppt","pptx"};
        List<String> imageTypeList= Arrays.asList(imageType);
        List<String> officeTypeList= Arrays.asList(officeType);
        response.setContentType("text/html; charset=UTF-8");
        if(extensionName.equalsIgnoreCase("pdf")){
            response.setContentType("application/pdf");
            file=new File(path);
        }else if(imageTypeList.contains(extensionName)){
            response.setContentType("image/"+extensionName);
            file=new File(path);
        }else if(officeTypeList.contains(extensionName)){
            //office文件转pdf再显示
            file = Office2PDF.openOfficeToPDF(path);
            response.reset(); // 非常重要
            response.setContentType("application/pdf");
        }else {
            throw new MyException("不支持的文件类型");
        }


        FileInputStream bis = null;
        OutputStream os = null;
        try {
            bis = new FileInputStream(file);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count =bis.read(buffer)) != -1){
                os.write(buffer, 0,count);
            }
            os.flush();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os !=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis !=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}










