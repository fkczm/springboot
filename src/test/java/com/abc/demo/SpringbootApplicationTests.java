package com.abc.demo;

import com.abc.demo._04mybatis.domain.Permission;
import com.abc.demo._04mybatis.service.IPermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class SpringbootApplicationTests {

	@Autowired
	private IPermissionService permissionService;
	@Test
	void contextLoads() {
		System.out.println(permissionService.selectAll());
	}

}
