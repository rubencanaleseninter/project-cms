package com.cms.core.models;

import java.util.Date;

public class Category {
	private int categoryId;
	private String name;
	private String description;
	private int categorySuperiorId;
	private Date date;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategorySuperiorId() {
		return categorySuperiorId;
	}

	public void setCategorySuperiorId(int categorySuperiorId) {
		this.categorySuperiorId = categorySuperiorId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
