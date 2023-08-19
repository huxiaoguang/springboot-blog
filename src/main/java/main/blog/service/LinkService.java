package main.blog.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.Link;

public interface LinkService {

	//友连管理
	public PageInfo<Link> listLink(Map<String, Object> param, String page);

	//友情链接
	public List<Link> getListLink(int limit);

	//友链详情
	public Link infoLink(int id);

	//添加友链
	public boolean addLink(Link link);

	//编辑友链
	public boolean editLink(Link link);

	//删除友链
	public boolean deleteLink(int id);

	//更新友链状态
	public boolean updateLinkStatus(Link link);
}
