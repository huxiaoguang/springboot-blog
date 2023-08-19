package main.blog.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.Comment;

public interface CommentService {

	/**
	 * 评论管理
	 * @param param
	 * @param page
	 * @return
	 */
	PageInfo<Comment> listComment(Map<String, Object> param, String page);

	/**
	 * 获取网站评论
	 * @param id
	 * @return
	 */
	List<Comment> getListComment(int id);

	/**
	 * 更新状态
	 * @param comment
	 * @return
	 */
	Boolean updateCommentStatus(Comment comment);

	/**
	 * 统计文章评论数
	 * @return
	 */
	int numComment();

	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	Boolean deleteComment(int id);
}
