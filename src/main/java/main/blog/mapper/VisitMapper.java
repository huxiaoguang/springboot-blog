package main.blog.mapper;

import org.springframework.stereotype.Repository;

import main.blog.entity.Visit;

@Repository
public interface VisitMapper {

	//访问统计
	boolean addVisit(Visit visit);

	//获取当天数据统计
	int getVisitDay(Visit visit);

	//更新访问次数
	boolean updateVisitNum(Visit visit);

	//统计总访问量
	int getVisitCount();
}
