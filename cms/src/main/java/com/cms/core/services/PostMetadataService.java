package com.cms.core.services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cms.core.models.PostMetadata;

@Service
public class PostMetadataService implements CrudService<PostMetadata, Integer> {
	private JdbcTemplate jdbcTemplate;

	public PostMetadataService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean save(PostMetadata postMetadata) {
		final String SQL = String.format("INSERT INTO `post_metadata`(postMetadata) VALUES('%s','%s','%s','%d')",
				postMetadata.getKey(), postMetadata.getValue(), postMetadata.getType(), postMetadata.getPostId());
		return this.jdbcTemplate.update(SQL, postMetadata.getPostMetadataId()) > 0;
	}

	@Override
	public boolean update(PostMetadata postMetadata) {
		final String SQL = String.format(
				"UPDATE `post_metadata` SET Key='%s', Value='%s', Type='%s', Post_id='%d' "
						+ "WHERE Post_metadata_id = '%d'",
				postMetadata.getKey(), postMetadata.getValue(), postMetadata.getType(), postMetadata.getPostId());
		return jdbcTemplate.update(SQL) > 0;
	}

	@Override
	public List<PostMetadata> findAll(Pageable pageable) {
		final String SQL = "SELECT * FROM `Post_metadata`";
		return this.jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(PostMetadata.class));
	}

	@Override
	public PostMetadata findById(Integer key) {
		final String SQL = String.format("SELECT * FROM `post_metadata` WHERE Post_metadata_id = '%d'", key);
		return this.jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(PostMetadata.class));
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
