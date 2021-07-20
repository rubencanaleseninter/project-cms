package com.cms.core.services;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cms.core.component.TestDbConfiguration;
import com.cms.core.models.Permission;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { TestDbConfiguration.class })
@TestMethodOrder(OrderAnnotation.class)
@Service
public class PermissionServiceTest {
	@Autowired
	private PermissionService permissionService;

	@Test
	@Order(1)
	public void testInsert() {
		Permission permission = new Permission();
		permission.setName("TestPermission");

		boolean result = permissionService.save(permission);

		Assert.assertTrue(result);
	}

	@Test
	@Order(2)
	public void testUpdate() {
		Permission permission = new Permission();
		permission.setPermissionId(1);
		permission.setName("TestPermission2");

		boolean result = permissionService.update(permission);

		Assert.assertTrue(result);
	}

	@Test
	@Order(3)
	public void testFindAll() {
		SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
		boolean permission = permissionService.findAll(pageable).isEmpty();

		Assert.assertFalse(permission);
	}

	@Test
	@Order(4)
	public void testFindById() {
		Permission permission = permissionService.findById(1);

		Assert.assertTrue(permission != null);
		Assert.assertTrue("TestPermission2".equals(permission.getName()));
	}

	@Test
	@Order(5)
	public void testDeleteById() {
		permissionService.deleteById(1);
		Permission permission = permissionService.findById(1);
		Assert.assertFalse(permission != null);
	}

	@Test
	@Order(6)
	public void testDeleteAll() {
		SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
		permissionService.deleteAll();

		Assert.assertTrue(permissionService.findAll(pageable).isEmpty());
	}
}
