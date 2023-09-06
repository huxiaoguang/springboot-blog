package main.blog.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.dto.admin.LinkSearchDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.dto.admin.TagSearchDTO;
import main.blog.entity.Link;

public interface LinkService {

	/**
	 * 友连管理
	 * @return
	 */
	PageInfo<Link> getLinkPage(LinkSearchDTO dto);

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
	Boolean deleteLink(Integer id);

	/**
	 * 更新友链状态
	 * @param dto
	 * @return
	 */
	Boolean updateLinkStatus(StatusDTO dto);
}
