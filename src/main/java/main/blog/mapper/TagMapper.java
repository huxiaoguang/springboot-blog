package main.blog.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.Tag;

@Repository
public interface TagMapper {

	//标签管理
    List<Tag> listTag(Map<String, Object> map);

    //添加标签
    Boolean addTag(Tag tag);

    //编辑标签
    Boolean editTag(Tag tag);

    //标签详情
    Tag infoTag(int id);

    //删除标签
    Boolean deleteTag(int id);

    //更新标签状态
    Boolean updateTagStatus(Tag tag);

    //外部调用
    List<Tag> getListTag(int limit);
}
