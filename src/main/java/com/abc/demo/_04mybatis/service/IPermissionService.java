package com.abc.demo._04mybatis.service;

import com.abc.demo._04mybatis.domain.Permission;
import com.abc.demo._04mybatis.queryObject.QueryForObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lihj on 2019/10/26.
 */
public interface IPermissionService {
    List<Permission> selectAll();
    int insert(Permission permission);
    void update(Permission permission);
    Permission get(Long id );
    List<Permission> queryForObject(QueryForObject queryForObject);
    Permission checkLogin(  String name ,   String resource);
    List<Permission> getPermissions(List<Integer> ids);
    int insertCache(Permission permission);
}
