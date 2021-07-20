package com.cms.core.services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cms.core.models.Group;

@Service
public class GroupService implements CrudService<Group, Integer> {
	private JdbcTemplate jdbcTemplate;

	public GroupService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean save(Group group) {
		final String SQL = String.format("INSERT INTO `group`(group) VALUES('%s','%d')", group.getName(),
				group.getGroupId());
		return this.jdbcTemplate.update(SQL, group.getGroupId()) > 0;
	}

	@Override
	public boolean update(Group group) {
		if (group.getGroupId() != 0) {
			final String SQL = String.format("UPDATE `group` SET Name='%s' " + "WHERE Group_id = '%d'", group.getName(),
					group.getGroupId());
			return jdbcTemplate.update(SQL) > 0;
		}
		return false;
	}

	@Override
	public List<Group> findAll(Pageable pageable) {
		final String SQL = "SELECT * FROM `group`";
		return this.jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Group.class));
	}

	@Override
	public Group findById(Integer key) {
		final String SQL = String.format("SELECT * FROM `group` WHERE Group_id = '%d'", key);
		return this.jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(Group.class));
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
