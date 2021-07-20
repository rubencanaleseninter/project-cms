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
import com.cms.core.models.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { TestDbConfiguration.class })
@TestMethodOrder(OrderAnnotation.class)
@Service
public class CategoryServiceTest {
	@Autowired
	private CategoryService categoryService;

	@Test
	@Order(1)
	public void testInsert() {
		Category category = new Category();
		category.setName("Test1");
		category.setDescription("Esto es un test de inserción");
		category.setCategorySuperiorId(1);

		boolean result = categoryService.save(category);

		Assert.assertTrue(result);
	}

	@Test
	@Order(2)
	public void testUpdate() {
		Category category = new Category();
		category.setCategoryId(45);
		category.setName("Test2");
		category.setDescription("Esto es un test de update 2");
		category.setCategorySuperiorId(2);

		boolean result = categoryService.update(category);

		Assert.assertTrue(result);
	}

	@Test
	@Order(3)
	public void testFindAll() {
		SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
		boolean category = categoryService.findAll(pageable).isEmpty();

		Assert.assertFalse(category);
	}

	@Test
	@Order(4)
	public void testFindById() {
		Category category = categoryService.findById(45);

		Assert.assertTrue(category != null);
		Assert.assertTrue("Test2".equals(category.getName()));
	}

	@Test
	@Order(5)
	public void testDeleteById() {
		categoryService.deleteById(47);
		Category category = categoryService.findById(51);
		Assert.assertFalse(category != null);
	}

	@Test
	@Order(6)
	public void testDeleteAll() {
		SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
		categoryService.deleteAll();

		Assert.assertTrue(categoryService.findAll(pageable).isEmpty());
	}
}
