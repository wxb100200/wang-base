package com.base.wang.controller;

import com.base.wang.common.MyException;
import com.base.wang.common.PageReturn;
import com.base.wang.common.Paginator;
import com.base.wang.convert.ModelConvert;
import com.base.wang.convert.PasswordConvert;
import com.base.wang.entity.BasTest;
import com.base.wang.service.TestService;
import com.base.wang.util.ExcelUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wxb on 2018/12/4.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private TestService testService;
    @ResponseBody
    @RequestMapping("/findById")
    public Object findById(){
        BasTest test = testService.findById(1);
        return test;
    }
    @ResponseBody
    @RequestMapping("/findById2")
    public Object findById2(){
        BasTest test = testService.findById2(1);
        return test;
    }
    @ResponseBody
    @RequestMapping("/findById3")
    public Object findById3(){
        BasTest test = testService.selectByKey(1);
        return test;
    }
    @ResponseBody
    @RequestMapping("/pageReturn")
    public Object pageReturn(){
        return PageReturn.success();
    }

    @ResponseBody
    @RequestMapping("/passValue")
    public Object passValue(String name){
        return PageReturn.success(name);
    }

    @ResponseBody
    @RequestMapping("/exception")
    public Object exception(){
        BasTest test = testService.selectByKey(1);
        throw new MyException("就是要抛异常");
    }
    @ResponseBody
    @RequestMapping("/pageHelper")
    public Object pageHelper(Paginator p){
        Object obj= testService.pageHelper(p);
        return  obj;
    }
    @ResponseBody
    @RequestMapping("/log4j")
    public Object log4j(){
        log.debug("---->>>>>>>>>log4j debug:");
        log.info("---->>>>>>>>>log4j info:");
        log.warn("---->>>>>>>>>log4j warn:");
        log.error("---->>>>>>>>log4j error:");
        return testService.log4j();
    }
    @ResponseBody
    @RequestMapping("/excel")
    public Object excel(HttpServletRequest request){
        List<BasTest> testList=testService.findAll();
        String[] cArray = {"用户名", "密码", "邮箱"};
        String[] eArray = {"Username", "Password", "Email"};
        ModelConvert[] converts = {null, new PasswordConvert(), null};
        List<String> cList = Arrays.asList(cArray);
        List<String> eList = Arrays.asList(eArray);
        List<ModelConvert> convertList = Arrays.asList(converts);
        String serverDir = request.getServletContext().getRealPath("/") + "export/";
        String excelFilePath = ExcelUtil.export("测试Excel导出", cList, eList, convertList, testList, serverDir);
        return PageReturn.success("导出Excel成功",excelFilePath);
    }
    @ResponseBody
    @RequestMapping("/excel2")
    public Object excel2(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<BasTest> testList=testService.findAll();
        String[] cArray = {"用户名", "密码", "邮箱"};
        String[] eArray = {"Username", "Password", "Email"};
        ModelConvert[] converts = {null, new PasswordConvert(), null};
        List<String> cList = Arrays.asList(cArray);
        List<String> eList = Arrays.asList(eArray);
        List<ModelConvert> convertList = Arrays.asList(converts);
        File file=ExcelUtil.export("测试Excel导出", cList, eList, convertList, testList);

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
        return PageReturn.success("导出Excel成功");
    }
}
