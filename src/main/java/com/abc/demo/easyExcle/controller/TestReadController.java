package com.abc.demo.easyExcle.controller;

import com.abc.demo.easyExcle.domain.Employee;
import com.abc.demo.easyExcle.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(value = "read")
    public List<Employee> simpleRead() throws Exception {
//    public List<DemoData> simpleRead(@RequestParam(value = "filePath") String filePath) throws Exception {
        String filePath="F:\\code-2019\\code-201911\\cloneCode\\easyExcel\\file\\DemoData.xlsx";
//        String filePath="DemoData.xlsx";
        List<Employee> employees = excelUtil.simpleRead(filePath);
        return employees;
    }

}
