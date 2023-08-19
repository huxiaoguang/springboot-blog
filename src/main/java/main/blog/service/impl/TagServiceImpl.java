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

@Service("tagService")
public class TagServiceImpl implements TagService
{
	@Autowired
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
	public boolean deleteTag(int id)
	{
		boolean result = tagMapper.deleteTag(id);
		return 	result;
	}

	@Override
	public boolean updateTagStatus(Tag tag)
	{
		boolean result = tagMapper.updateTagStatus(tag);
		return 	result;
	}

	@Override
	public boolean editTag(Tag tag)
	{
		boolean result = tagMapper.editTag(tag);
		return result;
	}

	@Override
	public Tag infoTag(int id)
	{
		Tag result = tagMapper.infoTag(id);
		return result;
	}

	@Override
	public boolean addTag(Tag tag)
	{
		boolean result = tagMapper.addTag(tag);
		return result;
	}
}
