package main.blog.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.blog.entity.Visit;
import main.blog.mapper.VisitMapper;
import main.blog.service.VisitService;

@Service("visitService")
public class VisitServiceImpl implements VisitService
{
	@Autowired
	private VisitMapper visitMapper;

	@Override
	public boolean addVisit(Visit visit)
	{
		int num = visitMapper.getVisitDay(visit);
		if(num==0)
		{
			visit.setCreateTime(new Date());
			visitMapper.addVisit(visit);
		}else {
			visitMapper.updateVisitNum(visit);
		}
		return true;
	}


	@Override
	public  int getVisitCount()
	{
		 int count = visitMapper.getVisitCount();
		 return count;
	}
}
