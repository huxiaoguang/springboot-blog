package main.blog.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.Link;

@Repository
public interface LinkMapper {

	//友链列表
	List<Link> listLink(Map<String, Object> param);

	//友链详情
	Link infoLink(int id);

	//添加友链
	boolean addLink(Link link);

	//编辑友链
	boolean editLink(Link link);

	//删除友链
	boolean deleteLink(int id);

	//更新友链状态
	boolean updateLinktatus(Link link);

	//外部调用
	List<Link> getListLink(int limit);
}
