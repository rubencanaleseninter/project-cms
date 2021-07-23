package com.cms.core.services;

import java.util.List;

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
	private Integer id;

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
	public void testFindAll() {
		SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
		boolean permission = permissionService.findAll(pageable).isEmpty();

		Assert.assertFalse(permission);
	}

	@Test
	@Order(3)
	public void testUpdate() {
		id = getLastPermissionId();

		Permission permission = new Permission();
		permission.setPermissionId(id);
		permission.setName("TestPermission2");

		boolean result = permissionService.update(permission);

		Assert.assertTrue(result);
	}

	@Test
	@Order(4)
	public void testFindById() {
		id = getLastPermissionId();

		Permission permission = permissionService.findById(id);

		Assert.assertTrue(permission != null);
		Assert.assertTrue("TestPermission2".equals(permission.getName()));
	}

	@Test
	@Order(5)
	public void testDeleteById() {
		id = getLastPermissionId();

		permissionService.deleteById(id);

		Assert.assertFalse(permissionService.deleteById(id));
	}

	@Test
	@Order(6)
	public void testDeleteAll() {
		SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
		permissionService.deleteAll();

		Assert.assertTrue(permissionService.findAll(pageable).isEmpty());
	}

	public Integer getLastPermissionId() {
		SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
		List<Permission> permissions = permissionService.findAll(pageable);
		id = permissions.get(permissions.size() - 1).getPermissionId();
		return id;
	}
}
