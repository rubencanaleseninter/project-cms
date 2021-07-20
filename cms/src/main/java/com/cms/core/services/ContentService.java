package com.cms.core.services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cms.core.models.Content;

@Service
public class ContentService implements CrudService<Content, Integer> {
	private JdbcTemplate jdbcTemplate;

	public ContentService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean save(Content content) {
		final String SQL = String.format("INSERT INTO `content`(content) VALUES('%s','%s','%d','%d')",
				content.getType(), content.getContent(), content.getPostId(), content.getContentId());
		return this.jdbcTemplate.update(SQL, content.getContentId()) > 0;
	}

	@Override
	public boolean update(Content content) {
		final String SQL = String.format("UPDATE `content` SET Type='%s', Content='%s' " + "WHERE Content_id = '%d'",
				content.getType(), content.getContent(), content.getContentId());
		return jdbcTemplate.update(SQL) > 0;

	}

	@Override
	public List<Content> findAll(Pageable pageable) {
		final String SQL = "SELECT * FROM `content`";
		return this.jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Content.class));
	}

	@Override
	public Content findById(Integer key) {
		final String SQL = String.format("SELECT * FROM `content` WHERE Content_id = '%d'", key);
		return this.jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(Content.class));
	}

	@Override
	public boolean deleteById(Integer key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}
}
