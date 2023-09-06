package main.blog.mapper;

import java.util.List;
import java.util.Map;

import main.blog.dto.admin.StatusDTO;
import main.blog.dto.admin.TagSearchDTO;
import org.springframework.stereotype.Repository;

import main.blog.entity.Tag;

@Repository
public interface TagMapper {

    /**
     * 标签管理
     * @param dto
     * @return
     */
    List<Tag> getTagPage(TagSearchDTO dto);

    /**
     * 添加标签
     * @param dto
     * @return
     */
    Boolean addTag(Tag dto);

    /**
     * 编辑标签
     * @param tag
     * @return
     */
    Boolean editTag(Tag tag);

    /**
     * 标签详情
     * @param id
     * @return
     */
    Tag infoTag(Integer id);

    /**
     * 删除标签
     * @param id
     * @return
     */
    Boolean deleteTag(Integer id);

    /**
     * 更新标签状态
     * @param dto
     * @return
     */
    Boolean updateTagStatus(StatusDTO dto);

    /**
     * 外部调用
     * @param limit
     * @return
     */
    List<Tag> getListTag(int limit);
}
