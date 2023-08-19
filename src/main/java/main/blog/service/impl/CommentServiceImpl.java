package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import main.blog.entity.Comment;
import main.blog.mapper.CommentMapper;
import main.blog.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService
{
	@Autowired
    private CommentMapper commentMapper;

	@Override
	public List<Comment> getListComment(int id)
	{
		List<Comment> list = commentMapper.getListComment(id);
		return list;
	}

	@Override
	public boolean updateCommentStatus(Comment comment)
	{
		boolean result = commentMapper.updateCommentStatus(comment);
		return  result;
	}

	@Override
	public PageInfo<Comment> listComment(Map<String, Object> param, String page)
	{
		int P = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);

		PageHelper.startPage(P, 15);
		List<Comment> list = commentMapper.listComment(param);
		PageInfo<Comment> pageinfo = new PageInfo<Comment>(list);

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
