package com.abc.demo._04mybatis.controller;

import com.abc.demo._04mybatis.domain.Permission;
import com.abc.demo._04mybatis.queryObject.QueryForObject;
import com.abc.demo._04mybatis.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lihj on 2019/10/26.
 */
@Controller

public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
    @RequestMapping("/list")
    @ResponseBody
    public List<Permission> list(){
        return permissionService.selectAll();
    }
    @RequestMapping("/add")
    @ResponseBody
    public String  add(Long id, @RequestParam("myname")String name,  String resource, @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date hiretime){
        System.out.println(hiretime);
        Permission permission= new Permission();
        permission.setId(id);
        permission.setName(name);
        permission.setResource(resource);
        permission.setHiretime(new Date());
        int primaryKey = permissionService.insertCache(permission);
        return "success:add:primaryKey="+primaryKey;
    }
    @RequestMapping("/update")
    @ResponseBody
    public String  update(Permission permission){
//        Permission permission= new Permission();
//        permission.setId(id);
//        permission.setName(name);
//        permission.setResource(resource);
//        permission.setHiretime(new Date());
        permissionService.update(permission);
        return "success:update";
    }
    @RequestMapping("/get")
    @ResponseBody
    public Permission  get(Long id ){

        return permissionService.get(id);
    }
    @RequestMapping("/query")
    @ResponseBody
    public    List<Permission> queryForObject(QueryForObject  queryForObject){
        return permissionService.queryForObject(queryForObject);
    }
    @RequestMapping("/check")
    @ResponseBody
    public    Permission checkLogin(String name, String resource){
        return permissionService.checkLogin(  name,   resource);
    }

    @RequestMapping("/getPermissions")
    @ResponseBody
    public List<Permission> getPermissions(@RequestParam(value = "name", defaultValue = "lihj",required = false) String name, String resource){
        List<Integer> ids =new ArrayList<>();
        System.out.println(name);
        ids.add(1);
        ids.add(2);
        return permissionService.getPermissions( ids );
    }

}
