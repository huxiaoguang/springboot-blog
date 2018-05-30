package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import main.blog.entity.TagBean;
import main.blog.mapper.TagMapper;
import main.blog.service.TagService;

@Service("tagService") 
public class TagServiceImpl implements TagService
{	
	@Autowired
    private TagMapper tagMapper;
	
	@Override
	public List<TagBean> getListTag(int limit) 
	{	
		List<TagBean> list = tagMapper.getListTag(limit);
		return list;
	}
	
	@Override
	public PageInfo<TagBean> listTag(Map<String, Object> param, String page) 
	{	
		int P = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);
		
		PageHelper.startPage(P, 15);
		List<TagBean> list = tagMapper.listTag(param);
		PageInfo<TagBean> pageinfo = new PageInfo<TagBean>(list);
		
		return pageinfo;
	}
	
	@Override
	public boolean deleteTag(int id) 
	{
		boolean result = tagMapper.deleteTag(id);
		return 	result;
	}
	
	@Override
	public boolean updateTagStatus(TagBean tag) 
	{
		boolean result = tagMapper.updateTagStatus(tag);
		return 	result;
	}

	@Override
	public boolean editTag(TagBean tag) 
	{
		boolean result = tagMapper.editTag(tag);
		return result;
	}

	@Override
	public TagBean infoTag(int id) 
	{
		TagBean result = tagMapper.infoTag(id);
		return result;
	}
	
	@Override
	public boolean addTag(TagBean tag) 
	{
		boolean result = tagMapper.addTag(tag);
		return result;
	}
}
