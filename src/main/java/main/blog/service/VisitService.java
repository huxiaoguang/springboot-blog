package main.blog.service;

import main.blog.entity.Visit;

public interface VisitService
{
	//访问统计
	public boolean addVisit(Visit visit);

	//统计总访问量
	public int getVisitCount();
}
