package com.cms.core.services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cms.core.models.Permission;

@Service
public class PermissionService implements CrudService<Permission, Integer> {
	private JdbcTemplate jdbcTemplate;

	public PermissionService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean save(Permission permission) {
		String SQL = String.format("INSERT INTO `permission`(`Permission_id`,`Name`) VALUES('%d','%s')",
				permission.getPermissionId(), permission.getName());
		return this.jdbcTemplate.update(SQL) > 0;
	}

	@Override
	public boolean update(Permission permission) {
		final String SQL = String.format("UPDATE `permission` SET Name='%s' " + "WHERE Permission_id = '%d'",
				permission.getName(), permission.getPermissionId());
		return jdbcTemplate.update(SQL) > 0;
	}

	@Override
	public boolean deleteById(Integer key) {
		final String SQL = String.format("DELETE FROM `permission`" + "WHERE Permission_id = '%d'", key);
		return jdbcTemplate.update(SQL) > 0;
	}

	@Override
	public boolean deleteAll() {
		this.jdbcTemplate.execute("DELETE FROM `permission`");
		return true;
	}

	@Override
	public List<Permission> findAll(Pageable pageable) {
		final String SQL = "SELECT * FROM `permission`";
		return this.jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Permission.class));
	}

	@Override
	public Permission findById(Integer key) {
		final String SQL = String.format("SELECT * FROM `permission` WHERE Permission_id = '%d'", key);
		return this.jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(Permission.class));
	}

}
