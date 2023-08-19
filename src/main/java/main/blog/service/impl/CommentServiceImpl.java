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

import javax.annotation.Resource;

@Service("commentService")
public class CommentServiceImpl implements CommentService
{
	@Resource
    private CommentMapper commentMapper;

	@Override
	public List<Comment> getListComment(int id)
	{
		List<Comment> list = commentMapper.getListComment(id);
		return list;
	}

	@Override
	public Boolean updateCommentStatus(Comment comment)
	{
		return commentMapper.updateCommentStatus(comment);
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
	public Boolean deleteComment(int id)
	{
		return commentMapper.deleteComment(id);
	}

	@Override
	public int numComment()
	{
		return commentMapper.numComment();
	}
}
