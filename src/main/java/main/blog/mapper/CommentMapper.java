package main.blog.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.CommentBean;

@Repository
public interface CommentMapper {
	
	//评论管理
	public List<CommentBean> listComment(Map<String, Object> param);
	
	//删除友链
	public boolean deleteComment(int id);
	
	//获取文章评论
	public List<CommentBean> getListComment(int id);
	
	//统计文章评论数
	public int countComment(int id);
	
	//统计文章评论数
	public int numComment();
		
	//更新评论状态
	public boolean updateCommentStatus(CommentBean comment);
}
