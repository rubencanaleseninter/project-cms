package com.cms.core.services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cms.core.models.UserMetadata;

public class UserMetadataService implements CrudService<UserMetadata, Integer> {
	private JdbcTemplate jdbcTemplate;

	public UserMetadataService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean save(UserMetadata userMetadata) {
		final String SQL = String.format("INSERT INTO `user_metadata`(userMetadata) VALUES('%s','%s','%s','%d')",
				userMetadata.getKey(), userMetadata.getValue(), userMetadata.getType(), userMetadata.getPostId());
		return this.jdbcTemplate.update(SQL, userMetadata.getUserMetadataId()) > 0;
	}

	@Override
	public boolean update(UserMetadata userMetadata) {
		final String SQL = String.format(
				"UPDATE `user_metadata` SET Key='%s', Value='%s', Type='%s', Post_id='%d' "
						+ "WHERE User_metadata_id = '%d'",
				userMetadata.getKey(), userMetadata.getValue(), userMetadata.getType(), userMetadata.getPostId(),
				userMetadata.getUserMetadataId());
		return jdbcTemplate.update(SQL) > 0;
	}

	@Override
	public List<UserMetadata> findAll(Pageable pageable) {
		final String SQL = "SELECT * FROM `User_metadata`";
		return this.jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(UserMetadata.class));
	}

	@Override
	public UserMetadata findById(Integer key) {
		final String SQL = String.format("SELECT * FROM `user_metadata` WHERE User_metadata_id = '%d'", key);
		return this.jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(UserMetadata.class));
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
