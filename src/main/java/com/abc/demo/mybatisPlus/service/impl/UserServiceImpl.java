package com.abc.demo.mybatisPlus.service.impl;

import com.abc.demo.mybatisPlus.domain.User;
import com.abc.demo.mybatisPlus.mapper.UserMapper;
import com.abc.demo.mybatisPlus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created on 2019/12/18 in springboot 项目 .
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
