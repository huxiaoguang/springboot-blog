package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import main.blog.entity.AdminLogBean;
import main.blog.mapper.AdminLogMapper;
import main.blog.service.AdminLogService;

@Service("adminLogService")
public class AdminLogServiceImpl implements AdminLogService 
{
	@Autowired
	private AdminLogMapper adminLogMapper;
	
	@Override
	public PageInfo<AdminLogBean> listAdminLog(Map<String, Object> param, String page) 
	{	
		int P = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);
		
		PageHelper.startPage(P, 15);
		List<AdminLogBean> list = adminLogMapper.listAdminLog(param);
		PageInfo<AdminLogBean> pageinfo = new PageInfo<AdminLogBean>(list);
		
		return pageinfo;
	}
	
	@Override
	public boolean deleteAdminLog(int id) 
	{
		boolean result = adminLogMapper.deleteAdminLog(id);
		return 	result;
	}
}
