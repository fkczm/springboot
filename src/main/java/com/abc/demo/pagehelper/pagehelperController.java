package com.abc.demo.pagehelper;

import com.abc.demo.easyExcle.domain.Employee;
import com.abc.demo.easyExcle.mapper.EmployeeMapper;
import com.abc.demo.easyExcle.utils.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wyy
 * @version 1.0
 * @date 2019/11/21 16:07
 * @description
 **/
@RestController
public class pagehelperController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @ApiOperation(value = "分页查询",notes = "分页查询所有Employee")
    @GetMapping(value = "/page")
    public PageInfo<Employee> pageHelper(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
       JSON json= new JSONObject();
        PageHelper.startPage(1,5);
       List<Employee> list= employeeMapper.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

}
