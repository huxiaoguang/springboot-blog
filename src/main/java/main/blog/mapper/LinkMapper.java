package main.blog.mapper;

import java.util.List;
import java.util.Map;

import main.blog.dto.admin.LinkSearchDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.dto.admin.TagSearchDTO;
import org.springframework.stereotype.Repository;

import main.blog.entity.Link;

@Repository
public interface LinkMapper {

	/**
	 * 友链列表
	 * @param dto
	 * @return
	 */
	List<Link> getLinkPage(LinkSearchDTO dto);

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
	Boolean updateLinktatus(StatusDTO dto);

	/**
	 * 外部调用
	 * @param limit
	 * @return
	 */
	List<Link> getListLink(int limit);
}
