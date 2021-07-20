package com.cms.core.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cms.core.models.User;

@Service
public class UserService implements CrudService<User, Integer> {
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean save(User user) {
		final String SQL = String.format("INSERT INTO `post`(post) VALUES('%s','%s','%s','%d','%d','%s','%s')",
				user.getName(), user.getSurname(), user.getLastname(), user.getPassword(), user.getEmail(),
				user.getGroupId());
		return this.jdbcTemplate.update(SQL, user.getUserId()) > 0;
	}

	@Override
	public boolean update(User user) {
		final String SQL = String.format(
				"UPDATE `user` SET Name='%s', Surname='%s', Lastname='%s', Password='%s', Email='%s', Group_id='%d' "
						+ "WHERE User_id = '%d'",
				user.getName(), user.getSurname(), user.getLastname(), user.getPassword(), user.getEmail(),
				user.getGroupId(), user.getUserId());
		return jdbcTemplate.update(SQL) > 0;
	}

	@Override
	public List<User> findAll(Pageable pageable) {
		final String SQL = "SELECT * FROM `Post`";
		return this.jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(User.class));
	}

	@Override
	public User findById(Integer key) {
		final String SQL = String.format("SELECT * FROM `user` WHERE User_id = '%d'", key);
		return this.jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(User.class));
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
