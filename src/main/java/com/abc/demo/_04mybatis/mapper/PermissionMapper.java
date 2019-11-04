package com.abc.demo._04mybatis.mapper;
import com.abc.demo._04mybatis.domain.Permission;
import com.abc.demo._04mybatis.queryObject.QueryForObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface  PermissionMapper {
    List<Permission> selectAll();
    int insert(Permission permission);
    void update(Permission permission);
    Permission get(Long id );
    List<Permission> queryForObject(QueryForObject queryForObject);
    List<Permission> getPermissions(@Param("ids") List<Integer> ids);

    //对应xml里注的SQL
    //List<Permission> getPermissions(List<Integer> ids);
    Permission checkLogin(@Param("name") String name ,@Param("resource") String resource);
}