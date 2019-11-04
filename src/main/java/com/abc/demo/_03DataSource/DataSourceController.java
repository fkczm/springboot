package com.abc.demo._03DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by lihj on 2019/10/26.
 */
@Controller
public class DataSourceController {
    @Autowired
    private DataSource dataSource;//会出现红色下划线,这是idea的通病，对程序没有影响。因为用的项目的启动类SpringbootApplication

    @ResponseBody
    @RequestMapping("/dataSource/{id}/{name}")
    //若id和name类型一样，方法dataSource1(@@PathVariable("name") String  name，PathVariable("id") int id)会和dataSource报模糊错误
    public String dataSource(@PathVariable("id") int id,@PathVariable("name") String  name) throws Exception {
        System.out.println("restful风格的id："+id);
        System.out.println(dataSource.getConnection());
        System.out.println(dataSource);
        return "druidDataSource";
    }
}
