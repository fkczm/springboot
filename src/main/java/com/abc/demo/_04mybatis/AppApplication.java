package com.abc.demo._04mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@EnableCaching
@SpringBootApplication
@MapperScan("com.abc.demo._04mybatis.mapper")//具体到mapper包下，否则扩大扫描路径会给service和controller(mapper吧)生成代理对象，service就有2个实现类
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
