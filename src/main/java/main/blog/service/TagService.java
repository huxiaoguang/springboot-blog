package main.blog.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.dto.admin.TagSearchDTO;
import main.blog.entity.Tag;

public interface TagService {

	/**
	 * Tag管理
	 * @return
	 */
	PageInfo<Tag> listTag(TagSearchDTO dto);

	/**
	 * 网站标签
	 * @param limit
	 * @return
	 */
	List<Tag> getListTag(int limit);

	/**
	 * 标签详情
	 * @param id
	 * @return
	 */
    Tag infoTag(int id);

	/**
	 * 删除Tag
	 * @param id
	 * @return
	 */
	Boolean deleteTag(int id);

	/**
	 * 更新状态
	 * @param tag
	 * @return
	 */
	Boolean updateTagStatus(Tag tag);

	/**
	 * 添加标签
	 * @param tag
	 * @return
	 */
	Boolean addTag(Tag tag);

	/**
	 * 编辑标签
	 * @param tag
	 * @return
	 */
	Boolean editTag(Tag tag);
}
