package com.cms.core.services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cms.core.models.PermissionGroup;

@Service
public class PermissionGroupService implements CrudService<PermissionGroup, Integer> {
	private JdbcTemplate jdbcTemplate;

	public PermissionGroupService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean save(PermissionGroup permissionGroup) {
		final String SQL = "INSERT INTO `permission_group`(permissionGroup) VALUES(?)";
		return this.jdbcTemplate.update(SQL, permissionGroup.getGroupId()) > 0;
	}

	@Override
	public boolean update(PermissionGroup permissionGroup) {
		final String SQL = String.format("UPDATE `permission_group` SET Permission_id='%d' " + "WHERE Group_id = '%d'",
				permissionGroup.getPermissionId(), permissionGroup.getGroupId());
		return jdbcTemplate.update(SQL) > 0;

	}

	@Override
	public List<PermissionGroup> findAll(Pageable pageable) {
		final String SQL = "SELECT * FROM `permission_group`";
		return this.jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(PermissionGroup.class));
	}

	@Override
	public PermissionGroup findById(Integer key) {
		final String SQL = String.format("SELECT * FROM `Permission_group` WHERE Group_id = '%d'", key);
		return this.jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(PermissionGroup.class));
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
