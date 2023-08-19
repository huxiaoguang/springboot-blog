package main.blog.service;

import main.blog.entity.Visit;

public interface VisitService
{
	/**
	 * 访问统计
	 * @param visit
	 * @return
	 */
	Boolean addVisit(Visit visit);

	/**
	 * 统计总访问量
	 * @return
	 */
	int getVisitCount();
}
