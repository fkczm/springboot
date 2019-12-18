package com.abc.demo.pagehelper;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@EnableCaching
@SpringBootApplication
@MapperScan("com.abc.demo.easyExcle.mapper")//具体到mapper包下，否则扩大扫描路径会给service和controller(mapper吧)生成代理对象，service就有2个实现类
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	@Bean
	PageHelper pageHelper(){
		//分页插件
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);
//添加插件
		new SqlSessionFactoryBean().setPlugins(new Interceptor[]{(Interceptor) pageHelper});
		return pageHelper;

	}
}
