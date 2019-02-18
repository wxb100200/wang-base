package com.base.wang.controller;

import com.base.wang.common.PageReturn;
import com.base.wang.entity.BasTest;
import com.base.wang.excel.ExcelSheetPO;
import com.base.wang.excel.ExcelUtil;
import com.base.wang.excel.ExcelVersion;
import com.base.wang.service.TestService;
import com.base.wang.util.UUIDUtil;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxb on 2019/1/15.
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private TestService testService;


    @ResponseBody
    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public Object read( @RequestParam("file") MultipartFile multfile){
        // 获取文件名
        String fileName = multfile.getOriginalFilename();
        // 获取文件后缀
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        List<ExcelSheetPO> sheetPOList=new ArrayList<>();
        try {
            // 用uuid作为文件名，防止生成的临时文件重复
            File excelFile = File.createTempFile(UUIDUtil.generateNumber(), prefix);
            // MultipartFile to File
            multfile.transferTo(excelFile);
            sheetPOList= ExcelUtil.readExcel(excelFile,10,3);
            //程序结束时，删除临时文件
            deleteFile(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(ExcelSheetPO po:sheetPOList){
            System.out.println("--->>>>sheetName:"+po.getSheetName());
        }
        return sheetPOList;
    }
    /**
     * 删除
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
    @RequestMapping("/downloadExcel")
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ExcelSheetPO po=new ExcelSheetPO();
        po.setSheetName("下载记录");
        po.setTitle("我是标题");
        String[] headers={"用户名", "密码", "邮箱"};
        po.setHeaders(headers);
        List<BasTest> testList = testService.findAll();
        List<List<Object>> dataList=new ArrayList<>();
        for(BasTest test:testList){
            List<Object> list=new ArrayList<>();
            list.add(test.getUsername());
            list.add(test.getPassword());
            list.add(test.getEmail());
            dataList.add(list);
        }
        po.setDataList(dataList);

        List<ExcelSheetPO> excelSheets=new ArrayList<>();
        excelSheets.add(po);
        File file=ExcelUtil.downloadExcel(ExcelVersion.V2007,excelSheets);
        String fileName="测试文件下载.xls";
        String userAgent = request.getHeader("user-agent").toLowerCase();
        String downloadFileName;
        if (userAgent.contains("msie") || userAgent.contains("like gecko") ) {
            // win10 ie edge 浏览器 和其他系统的ie
            downloadFileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            // fe
            downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        }
        response.addHeader("content-disposition", "attachment;filename="+downloadFileName);
        FileUtils.copyFile(file, response.getOutputStream());
    }
}
