package main.blog.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.CommentBean;

public interface CommentService {
	
	//评论管理
	public PageInfo<CommentBean> listComment(Map<String, Object> param, String page);
	
	//获取网站评论
	public List<CommentBean> getListComment(int id);
	
	//更新状态
	public boolean updateCommentStatus(CommentBean comment);
	
	//统计文章评论数
	public int numComment();
		
	//删除评论
	public boolean deleteComment(int id);	
}
