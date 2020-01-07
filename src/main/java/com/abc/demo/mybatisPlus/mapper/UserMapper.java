package com.abc.demo.mybatisPlus.mapper;

import com.abc.demo.mybatisPlus.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created on 2019/12/18 in springboot 项目 .
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
