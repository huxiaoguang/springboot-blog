package main.blog.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.Link;

public interface LinkService {

	/**
	 * 友连管理
	 * @param param
	 * @param page
	 * @return
	 */
	PageInfo<Link> listLink(Map<String, Object> param, String page);

	/**
	 * 友情链接
	 * @param limit
	 * @return
	 */
	List<Link> getListLink(int limit);

	/**
	 * 友链详情
	 * @param id
	 * @return
	 */
	Link infoLink(int id);

	/**
	 * 添加友链
	 * @param link
	 * @return
	 */
	Boolean addLink(Link link);

	/**
	 * 编辑友链
	 * @param link
	 * @return
	 */
	Boolean editLink(Link link);

	/**
	 * 删除友链
	 * @param id
	 * @return
	 */
	Boolean deleteLink(int id);

	/**
	 * 更新友链状态
	 * @param link
	 * @return
	 */
	Boolean updateLinkStatus(Link link);
}
