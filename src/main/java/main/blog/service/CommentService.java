package main.blog.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.Comment;

public interface CommentService {

	//评论管理
	public PageInfo<Comment> listComment(Map<String, Object> param, String page);

	//获取网站评论
	public List<Comment> getListComment(int id);

	//更新状态
	public boolean updateCommentStatus(Comment comment);

	//统计文章评论数
	public int numComment();

	//删除评论
	public boolean deleteComment(int id);
}
