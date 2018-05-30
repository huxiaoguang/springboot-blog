package main.blog.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.TagBean;


public interface TagService {
	
	//Tag管理
	public PageInfo<TagBean> listTag(Map<String, Object> param, String page);
		
	//网站标签
	public List<TagBean> getListTag(int limit);
	
	//标签详情
    public TagBean infoTag(int id);
    
	//删除Tag
	public boolean deleteTag(int id);
	
	//更新状态
	public boolean updateTagStatus(TagBean tag);
	
	//添加标签
    public boolean addTag(TagBean tag);
    
	//编辑标签
    public boolean editTag(TagBean tag);
}