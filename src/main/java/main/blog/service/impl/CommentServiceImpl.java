package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import main.blog.entity.CommentBean;
import main.blog.mapper.CommentMapper;
import main.blog.service.CommentService;

@Service("commentService") 
public class CommentServiceImpl implements CommentService
{	
	@Autowired
    private CommentMapper commentMapper;
	
	@Override
	public List<CommentBean> getListComment(int id) 
	{
		List<CommentBean> list = commentMapper.getListComment(id);
		return list;
	}
	
	@Override
	public boolean updateCommentStatus(CommentBean comment) 
	{
		boolean result = commentMapper.updateCommentStatus(comment);
		return  result;
	}
	
	@Override
	public PageInfo<CommentBean> listComment(Map<String, Object> param, String page) 
	{	
		int P = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);
		
		PageHelper.startPage(P, 15);
		List<CommentBean> list = commentMapper.listComment(param);
		PageInfo<CommentBean> pageinfo = new PageInfo<CommentBean>(list);
		
		return pageinfo;
	}
	
	@Override
	public boolean deleteComment(int id) 
	{	
		boolean result = commentMapper.deleteComment(id);
		return  result;
	}
	
	@Override
	public int numComment() 
	{
		return commentMapper.numComment();
	}
}
