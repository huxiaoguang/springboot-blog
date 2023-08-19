package main.blog.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.Tag;


public interface TagService {

	//Tag管理
	public PageInfo<Tag> listTag(Map<String, Object> param, String page);

	//网站标签
	public List<Tag> getListTag(int limit);

	//标签详情
    public Tag infoTag(int id);

	//删除Tag
	public boolean deleteTag(int id);

	//更新状态
	public boolean updateTagStatus(Tag tag);

	//添加标签
    public boolean addTag(Tag tag);

	//编辑标签
    public boolean editTag(Tag tag);
}
