package com.abc.demo.easyExcle.controller;

import com.abc.demo.easyExcle.utils.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author wyy
 * @version 1.0
 * @date 2019/11/21 16:07
 * @description
 **/
@RestController
public class TestReadController {
    @Autowired
    private ExcelUtil excelUtil;
    private  final Logger Logger = LoggerFactory.getLogger(TestReadController.class);
    @RequestMapping(value = "read")
    public Map<String, Object> simpleRead(MultipartFile file)  {
//    public List<DemoData> simpleRead(@RequestParam(value = "filePath") String filePath) throws Exception {
        String filePath="F:/code-2019/code-201911/cloneCode/easyExcel/file/DemoData.xlsx";
//        String filePath="DemoData.xlsx";
        Map<String, Object> map = null;
        try {
            map = excelUtil.simpleRead(filePath);
        } catch (Exception e) {
            Logger.info("读取excel异常={}",e);
        }


        return map;
    }

}
