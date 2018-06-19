package main.blog.service;

import main.blog.entity.VisitBean;

public interface VisitService
{	
	//访问统计
	public boolean addVisit(VisitBean visit);
	
	//统计总访问量
	public int getVisitCount();
}
