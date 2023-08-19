package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import main.blog.entity.Link;
import main.blog.mapper.LinkMapper;
import main.blog.service.LinkService;

@Service("linkService")
public class LinkServiceImpl implements LinkService
{
	@Autowired
    private LinkMapper linkMapper;

	@Override
	public List<Link> getListLink(int limit)
	{
		List<Link> list = linkMapper.getListLink(limit);
		return list;
	}

	@Override
	public PageInfo<Link> listLink(Map<String, Object> param, String page)
	{
		int P = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);

		PageHelper.startPage(P, 15);
		List<Link> list = linkMapper.listLink(param);
		PageInfo<Link> pageinfo = new PageInfo<Link>(list);

		return pageinfo;
	}

	@Override
	public boolean addLink(Link link)
	{
		boolean result = linkMapper.addLink(link);
		return 	result;
	}

	@Override
	public Link infoLink(int id)
	{
		Link info = linkMapper.infoLink(id);
		return info;
	}

	@Override
	public boolean editLink(Link link)
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
	public boolean updateLinkStatus(Link link)
	{
		boolean result = linkMapper.updateLinktatus(link);
		return 	result;
	}
}
