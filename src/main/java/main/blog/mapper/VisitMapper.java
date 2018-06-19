package main.blog.mapper;

import org.springframework.stereotype.Repository;

import main.blog.entity.VisitBean;

@Repository
public interface VisitMapper {
	
	//访问统计
	public boolean addVisit(VisitBean visit);
	
	//获取当天数据统计
	public int getVisitDay(VisitBean visit);
	
	//更新访问次数
	public boolean updateVisitNum(VisitBean visit);
	
	//统计总访问量
	public int getVisitCount();
}