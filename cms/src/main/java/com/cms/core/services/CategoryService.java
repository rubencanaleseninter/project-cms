package com.cms.core.services;

import java.util.List;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cms.core.handle.CategoryMapper;
import com.cms.core.models.Category;

@Service
public class CategoryService implements CrudService<Category, Integer> {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean save(Category category) {
		final String SQL = "INSERT INTO `category`(Name, Description, Category_superior_id) VALUES (?,?,?)";
		
		log.info(SQL);
		return this.jdbcTemplate.update(SQL, category.getName(), category.getDescription(),
				category.getCategorySuperiorId()) > 0;
	}

	@Override
	public boolean update(Category category) {
		final String SQL = String.format(
				"UPDATE `category` SET Name=?, Description=?, Category_superior_id=? " + "WHERE Category_id = ?");
		
		log.info(SQL);
		return jdbcTemplate.update(SQL, category.getName(), category.getDescription(), category.getCategorySuperiorId(),
				category.getCategoryId()) > 0;
	}

	@Override
	public List<Category> findAll(Pageable pageable) {
		final String SQL = "SELECT * FROM `category`";
		
		log.info(SQL);
		return this.jdbcTemplate.query(SQL, new CategoryMapper());
	}

	@Override
	public Category findById(Integer key) {
		final String SQL = String.format("SELECT * FROM `category` WHERE Category_id = ?");
		
		log.info(SQL);
		return this.jdbcTemplate.queryForObject(SQL, new CategoryMapper(), key);
	}

	@Override
	public boolean deleteById(Integer key) {
		final String SQL = String.format("DELETE FROM `category` WHERE Category_id = ?");
		
		log.info(SQL);
		return jdbcTemplate.update(SQL, key) > 0;
	}

	@Override
	public boolean deleteAll() {
		final String SQL = String.format("DELETE FROM Category");
		
		log.info(SQL);
		return jdbcTemplate.update(SQL) > 0;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}