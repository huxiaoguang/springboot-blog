package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import main.blog.entity.Tag;
import main.blog.mapper.TagMapper;
import main.blog.service.TagService;

import javax.annotation.Resource;

@Service("tagService")
public class TagServiceImpl implements TagService
{
	@Resource
    private TagMapper tagMapper;

	@Override
	public List<Tag> getListTag(int limit)
	{
		List<Tag> list = tagMapper.getListTag(limit);
		return list;
	}

	@Override
	public PageInfo<Tag> listTag(Map<String, Object> param, String page)
	{
		int P = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);

		PageHelper.startPage(P, 15);
		List<Tag> list = tagMapper.listTag(param);
		PageInfo<Tag> pageinfo = new PageInfo<Tag>(list);
		return pageinfo;
	}

	@Override
	public Boolean deleteTag(int id)
	{
		return tagMapper.deleteTag(id);
	}

	@Override
	public Boolean updateTagStatus(Tag tag)
	{
		return tagMapper.updateTagStatus(tag);
	}

	@Override
	public Boolean editTag(Tag tag)
	{
		return tagMapper.editTag(tag);
	}

	@Override
	public Tag infoTag(int id)
	{
		return  tagMapper.infoTag(id);
	}

	@Override
	public Boolean addTag(Tag tag)
	{
		return tagMapper.addTag(tag);
	}
}
