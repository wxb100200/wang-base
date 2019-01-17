package com.base.wang.controller;

import com.base.wang.entity.BasTest;
import com.base.wang.excel.ExcelSheetPO;
import com.base.wang.excel.ExcelUtil;
import com.base.wang.util.UUIDUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxb on 2019/1/15.
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {
    private Logger log = Logger.getLogger(this.getClass());


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
            sheetPOList= ExcelUtil.readExcel(excelFile,10,10);
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
}
