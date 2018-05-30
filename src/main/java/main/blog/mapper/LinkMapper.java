package main.blog.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.LinkBean;

@Repository
public interface LinkMapper {
	
	//友链列表
	public List<LinkBean> listLink(Map<String, Object> param);
	
	//友链详情
	public LinkBean infoLink(int id);
	
	//添加友链
	public boolean addLink(LinkBean link);
		
	//编辑友链
	public boolean editLink(LinkBean link);
	
	//删除友链
	public boolean deleteLink(int id);
	
	//更新友链状态
	public boolean updateLinktatus(LinkBean link);
	
	//外部调用
	public List<LinkBean> getListLink(int limit);
}
