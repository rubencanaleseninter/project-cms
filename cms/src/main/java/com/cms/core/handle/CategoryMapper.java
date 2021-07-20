package com.cms.core.handle;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.cms.core.models.Category;

public class CategoryMapper implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category category = new Category();
		category.setCategorySuperiorId(rs.getInt("Category_superior_id"));
		category.setDescription(rs.getString("Description"));
		category.setDate(rs.getDate("Date"));
		category.setCategoryId(rs.getInt("Category_id"));
		category.setName(rs.getString("Name"));
		return category;
	}
}
