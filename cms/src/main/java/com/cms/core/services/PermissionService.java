package com.cms.core.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cms.core.models.Permission;

@Service
public class PermissionService implements CrudService<Permission, Integer> {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean save(Permission permission) {
		String SQL = String.format("INSERT INTO `permission`(`Permission_id`,`Name`) VALUES(?,?)");

		log.info(SQL);
		return this.jdbcTemplate.update(SQL, permission.getPermissionId(), permission.getName()) > 0;
	}

	@Override
	public boolean update(Permission permission) {
		final String SQL = String.format("UPDATE `permission` SET Name= ? " + "WHERE Permission_id = ?");

		log.info(SQL);
		return jdbcTemplate.update(SQL, permission.getName(), permission.getPermissionId()) > 0;
	}

	@Override
	public boolean deleteById(Integer key) {
		final String SQL = String.format("DELETE FROM `permission` WHERE Permission_id = ?");

		log.info(SQL);
		return jdbcTemplate.update(SQL, key) > 0;
	}

	@Override
	public boolean deleteAll() {
		final String SQL = String.format("DELETE FROM `permission`");
		this.jdbcTemplate.execute(SQL);

		log.info(SQL);
		return true;
	}

	@Override
	public List<Permission> findAll(Pageable pageable) {
		final String SQL = "SELECT * FROM `permission`";

		log.info(SQL);
		return this.jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Permission.class));
	}

	@Override
	public Permission findById(Integer key) {
		final String SQL = String.format("SELECT * FROM `permission` WHERE Permission_id = ?");

		log.info(SQL);
		return this.jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(Permission.class), key);
	}

}
