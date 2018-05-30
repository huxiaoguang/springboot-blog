package main.blog.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.AdminLogBean;

public interface AdminLogService {
	
	//管理员操作日志
	public PageInfo<AdminLogBean> listAdminLog(Map<String, Object> param, String page);
	
	//删除管理员日志
	public boolean deleteAdminLog(int id);
}
