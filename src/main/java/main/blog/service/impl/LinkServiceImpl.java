package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import main.blog.entity.LinkBean;
import main.blog.mapper.LinkMapper;
import main.blog.service.LinkService;

@Service("linkService") 
public class LinkServiceImpl implements LinkService
{	
	@Autowired  
    private LinkMapper linkMapper;
	
	@Override
	public List<LinkBean> getListLink(int limit)
	{	
		List<LinkBean> list = linkMapper.getListLink(limit);
		return list;
	}
	
	@Override
	public PageInfo<LinkBean> listLink(Map<String, Object> param, String page) 
	{	
		int P = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);
		
		PageHelper.startPage(P, 15);
		List<LinkBean> list = linkMapper.listLink(param);
		PageInfo<LinkBean> pageinfo = new PageInfo<LinkBean>(list);
		
		return pageinfo;
	}

	@Override
	public boolean addLink(LinkBean link) 
	{
		boolean result = linkMapper.addLink(link);
		return 	result;
	}

	@Override
	public LinkBean infoLink(int id) 
	{
		LinkBean info = linkMapper.infoLink(id);
		return info;
	}
	
	@Override
	public boolean editLink(LinkBean link) 
	{
		boolean result = linkMapper.editLink(link);
		return 	result;
	}
	
	@Override
	public boolean deleteLink(int id) 
	{
		boolean result = linkMapper.deleteLink(id);
		return 	result;
	}
	
	@Override
	public boolean updateLinkStatus(LinkBean link) 
	{
		boolean result = linkMapper.updateLinktatus(link);
		return 	result;
	}
}
