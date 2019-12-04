package com.abc.demo._04mybatis.service.impl;

import com.abc.demo._04mybatis.domain.Permission;
import com.abc.demo._04mybatis.mapper.PermissionMapper;
import com.abc.demo._04mybatis.queryObject.QueryForObject;
import com.abc.demo._04mybatis.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lihj on 2019/10/26.
 */
@Transactional
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private PermissionMapper permissionMapper1;
    @Autowired
    private RedisTemplate redisTemplate;
    @Transactional(readOnly = true)
    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public int insert(Permission permission) {
        return permissionMapper.insert(permission);
    }
    @CachePut(value = "perm:key:",key = "#permission.id")
    @Override
    public int insertCache(Permission permission) {
        return permissionMapper.insert(permission);
    }

    @Override
    public void update(Permission permission) {
         permissionMapper.update(permission);
    }

    @Override

    public Permission get(Long id) {
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        String key="get:id:"+id;
        Permission permission= null;

        permission=(Permission)redisTemplate.opsForValue().get(key);

        if (permission==null){
            synchronized (this){
                permission=(Permission)redisTemplate.opsForValue().get(key);
                if (permission==null){
                    permission=permissionMapper.get(id);
                    redisTemplate.opsForValue().set(key,permission);
                }
            }
        }
       return permission;
    }

    @Override
    public  List<Permission> queryForObject(QueryForObject queryForObject) {
        return permissionMapper.queryForObject( queryForObject);
    }

    @Override
    public Permission checkLogin(String name, String resource) {
        return permissionMapper.checkLogin(name,resource);
    }

    @Override
    public List<Permission> getPermissions(List<Integer> ids) {
        return permissionMapper.getPermissions(ids);
    }
}
