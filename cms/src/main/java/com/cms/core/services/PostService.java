package com.cms.core.services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cms.core.models.Post;

@Service
public class PostService implements CrudService<Post, Integer> {

	private JdbcTemplate jdbcTemplate;

	public PostService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean save(Post post) {
		final String SQL = String.format("INSERT INTO `post`(post) VALUES('%s','%s','%s','%d','%d','%s','%s')",
				post.getName(), post.getSlug(), post.getExtract(), post.getUserId(), post.getCategoryId(),
				post.getImg(), post.getType());
		return this.jdbcTemplate.update(SQL, post.getPostId()) > 0;
	}

	@Override
	public boolean update(Post post) {
		final String SQL = String.format(
				"UPDATE `post` SET Name='%s', Slug='%s', Extract='%s', User_id='%d', Category_id='%d', Img='%s', Type='%s' "
						+ "WHERE Post_metadata_id = '%d'",
				post.getName(), post.getSlug(), post.getExtract(), post.getUserId(), post.getCategoryId(),
				post.getImg(), post.getType(), post.getPostId());
		return jdbcTemplate.update(SQL) > 0;
	}

	@Override
	public List<Post> findAll(Pageable pageable) {
		final String SQL = "SELECT * FROM `Post`";
		return this.jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Post.class));
	}

	@Override
	public Post findById(Integer key) {
		final String SQL = String.format("SELECT * FROM `post` WHERE Post_id = '%d'", key);
		return this.jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(Post.class));
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
