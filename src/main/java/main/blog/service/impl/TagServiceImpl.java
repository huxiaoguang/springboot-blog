package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import main.blog.dto.admin.StatusDTO;
import main.blog.dto.admin.TagSearchDTO;
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
	public PageInfo<Tag> listTag(TagSearchDTO dto)
	{
		PageHelper.startPage(dto.getPage(), dto.getLimit());
		List<Tag> list = tagMapper.listTag(dto);
		return new PageInfo<>(list);
	}

	@Override
	public Boolean deleteTag(Integer id)
	{
		return tagMapper.deleteTag(id);
	}

	@Override
	public Boolean updateTagStatus(StatusDTO tag)
	{
		return tagMapper.updateTagStatus(tag);
	}

	@Override
	public Boolean editTag(Tag tag)
	{
		return tagMapper.editTag(tag);
	}

	@Override
	public Tag infoTag(Integer id)
	{
		return  tagMapper.infoTag(id);
	}

	@Override
	public Boolean addTag(Tag tag)
	{
		return tagMapper.addTag(tag);
	}
}
