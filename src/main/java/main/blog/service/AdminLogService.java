package main.blog.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.AdminLog;

public interface AdminLogService {

	/**
	 * 管理员操作日志
	 * @param param
	 * @param page
	 * @return
	 */
	PageInfo<AdminLog> listAdminLog(Map<String, Object> param, String page);

	/**
	 * 删除管理员日志
	 * @param id
	 * @return
	 */
	Boolean deleteAdminLog(Integer id);
}
