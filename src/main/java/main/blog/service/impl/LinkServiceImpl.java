package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import main.blog.dto.admin.LinkSearchDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.dto.admin.TagSearchDTO;
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
	public PageInfo<Link> listLink(LinkSearchDTO dto)
	{
		PageHelper.startPage(dto.getPage(), dto.getLimit());
		List<Link> list = linkMapper.listLink(dto);
		return new PageInfo<>(list);
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
	public Boolean deleteLink(Integer id)
	{
		return linkMapper.deleteLink(id);
	}

	@Override
	public Boolean updateLinkStatus(StatusDTO dto)
	{
		return linkMapper.updateLinktatus(dto);
	}
}
