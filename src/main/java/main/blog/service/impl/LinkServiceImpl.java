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

import javax.annotation.Resource;

@Service("linkService")
public class LinkServiceImpl implements LinkService
{
	@Resource
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
	public Boolean addLink(Link link)
	{
		return linkMapper.addLink(link);
	}

	@Override
	public Link infoLink(int id)
	{
		return linkMapper.infoLink(id);
	}

	@Override
	public Boolean editLink(Link link)
	{
		return linkMapper.editLink(link);
	}

	@Override
	public Boolean deleteLink(int id)
	{
		return linkMapper.deleteLink(id);
	}

	@Override
	public Boolean updateLinkStatus(Link link)
	{
		return linkMapper.updateLinktatus(link);
	}
}
