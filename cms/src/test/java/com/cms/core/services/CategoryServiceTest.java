package com.cms.core.services;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
	private Integer id;

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
	public void testFindAll() {
		SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
		List<Category> categories = categoryService.findAll(pageable);
		id = categories.get(categories.size() - 1).getCategoryId();

		boolean category = categories.isEmpty();

		Assert.assertFalse(category);
	}

	@Test
	@Order(3)
	public void testUpdate() {
		id = getLastCategoryId();

		Category category = new Category();
		category.setCategoryId(id);
		category.setName("Test2");
		category.setDescription("Esto es un test de update 2");
		category.setCategorySuperiorId(2);

		boolean result = categoryService.update(category);

		Assert.assertTrue(result);
	}

	@Test
	@Order(4)
	public void testFindById() {
		id = getLastCategoryId();

		Category category = categoryService.findById(id);

		Assert.assertTrue(category != null);
		Assert.assertTrue("Test2".equals(category.getName()));
	}

	@Test
	@Order(5)
	public void testDeleteById() {
		id = getLastCategoryId();

		categoryService.deleteById(id);

		Assert.assertFalse(categoryService.deleteById(id));
	}

	@Test
	@Order(6)
	public void testDeleteAll() {
		SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
		categoryService.deleteAll();

		Assert.assertTrue(categoryService.findAll(pageable).isEmpty());
	}

	public Integer getLastCategoryId() {
		SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
		List<Category> categories = categoryService.findAll(pageable);
		id = categories.get(categories.size() - 1).getCategoryId();
		return id;
	}
}
