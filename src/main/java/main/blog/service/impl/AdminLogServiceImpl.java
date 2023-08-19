package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import main.blog.entity.AdminLog;
import main.blog.mapper.AdminLogMapper;
import main.blog.service.AdminLogService;

import javax.annotation.Resource;

@Service("adminLogService")
public class AdminLogServiceImpl implements AdminLogService
{
	@Resource
	private AdminLogMapper adminLogMapper;

	@Override
	public PageInfo<AdminLog> listAdminLog(Map<String, Object> param, String page)
	{
		int P = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);
		PageHelper.startPage(P, 15);
		List<AdminLog> list = adminLogMapper.listAdminLog(param);
		PageInfo<AdminLog> pageinfo = new PageInfo<AdminLog>(list);
		return pageinfo;
	}

	@Override
	public Boolean deleteAdminLog(Integer id)
	{
		return adminLogMapper.deleteAdminLog(id);
	}
}
