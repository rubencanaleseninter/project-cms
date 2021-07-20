package com.cms.core.services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cms.core.models.Comment;
import com.cms.core.services.CommentService;

@Service
public class CommentService implements CrudService<Comment, Integer> {
	private JdbcTemplate jdbcTemplate;

	public CommentService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean save(Comment comment) {
		final String SQL = String.format(
				"INSERT INTO `Comment`(Comment, Post_id,User_id, Date, Response) "
						+ "values('%s','%d','%d', '%s', '%s') ",
				comment.getComment(), comment.getPostId(), comment.getUserId(), comment.getDate(),
				comment.getResponse());
		return jdbcTemplate.update(SQL, comment.getCommentId()) > 0;
	}

	@Override
	public boolean update(Comment comment) {
		final String SQL = String.format(
				"UPDATE `Comment` SET Comment='%s', Post_id='%d', User_id='%d', Date='%s', Response='%s') "
						+ "WHERE Comment_id = '%d'",
				comment.getComment(), comment.getPostId(), comment.getUserId(), comment.getDate(),
				comment.getResponse(), comment.getCommentId());
		return jdbcTemplate.update(SQL) > 0;
	}

	@Override
	public List<Comment> findAll(Pageable pageable) {
		final String SQL = "SELECT * FROM `comment`";
		return this.jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Comment.class));
	}

	@Override
	public Comment findById(Integer key) {
		final String SQL = String.format("SELECT * FROM `comment` WHERE Comment_id = '%d'", key);
		return this.jdbcTemplate.queryForObject(SQL, new BeanPropertyRowMapper<>(Comment.class));
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer key) {
		// TODO Auto-generated method stub
		return false;
	}
}