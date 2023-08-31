package main.blog.service;

import main.blog.dto.admin.EditPassDTO;
import main.blog.entity.Admin;

public interface AdminService {

	/**
	 * 获取管理员信息
	 * @param username
	 * @return
	 */
	Admin adminInfo(String username);

	/**
	 * 验证用户名和密码
	 * @param username
	 * @param password
	 * @return
	 */
	Boolean AuthAdmin(String username, String password);

	/**
	 * 用户登录接口
	 * @param username
	 * @param password
	 * @return
	 */
	Admin AdminLogin(String username, String password);

	/**
	 * 修改密码接口
	 * @return
	 */
	Boolean editPass(EditPassDTO dto);
}
