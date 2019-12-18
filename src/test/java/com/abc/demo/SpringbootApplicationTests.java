package com.abc.demo;

import com.abc.demo._04mybatis.domain.Permission;
import com.abc.demo._04mybatis.service.IPermissionService;
import com.abc.demo.mybatisPlus.domain.User;
import com.abc.demo.mybatisPlus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootApplicationTests {

	@Autowired
	private IPermissionService permissionService;
	@Test
	void contextLoads() {
		System.out.println(permissionService.selectAll());
	}

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userMapper.selectList(null);
		Assert.assertEquals(5, userList.size());
		userList.forEach(System.out::println);
	}
}
