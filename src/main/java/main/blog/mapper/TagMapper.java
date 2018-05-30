package main.blog.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.TagBean;

@Repository
public interface TagMapper {
	
	//标签管理
    public List<TagBean> listTag(Map<String, Object> map);
    
    //添加标签
    public boolean addTag(TagBean tag);
    
    //编辑标签
    public boolean editTag(TagBean tag);
    
    //标签详情
    public TagBean infoTag(int id);
    
    //删除标签
    public boolean deleteTag(int id);
    
    //更新标签状态
    public boolean updateTagStatus(TagBean tag);
    
    //外部调用
    public List<TagBean> getListTag(int limit);
}
