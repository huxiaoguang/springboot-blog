package main.blog.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.Comment;

@Repository
public interface CommentMapper {

	//评论管理
	List<Comment> listComment(Map<String, Object> param);

	//删除友链
	boolean deleteComment(int id);

	//获取文章评论
	List<Comment> getListComment(int id);

	//统计文章评论数
	int countComment(int id);

	//统计文章评论数
	int numComment();

	//更新评论状态
	boolean updateCommentStatus(Comment comment);
}
