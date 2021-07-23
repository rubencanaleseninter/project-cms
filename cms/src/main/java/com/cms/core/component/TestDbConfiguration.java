package com.cms.core.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.cms.core.services.CategoryService;
import com.cms.core.services.PermissionService;
import com.cms.core.services.UserService;

@Configuration
@ComponentScan(basePackages = "com.cms")
public class TestDbConfiguration {

	@Bean
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/test");
		dataSource.setUsername("sa");
		dataSource.setPassword(null);
		return dataSource;
	}

	@Bean
	public CategoryService categoryService() {
		return new CategoryService();
	}

	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public PermissionService permissionSermice() {
		return new PermissionService();
	}
}
