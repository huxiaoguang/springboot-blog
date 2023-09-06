package main.blog.service;

import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.AdminSaveDTO;
import main.blog.dto.admin.EditPassDTO;
import main.blog.dto.admin.AdminSearchDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.entity.Admin;

public interface AdminService
{
	/**
	 * 用户列表
	 * @param dto
	 * @return
	 */
	PageInfo<Admin> getAdminPage(AdminSearchDTO dto);

	/**
	 * 获取管理员信息
	 * @param adminId
	 * @return
	 */
	Admin getAdminInfo(Integer adminId);

	/**
	 * 获取管理员信息
	 * @param username
	 * @return
	 */
	Admin getAdminByUsername(String username);

	/**
	 * 新增用户
	 * @param dto
	 * @return
	 */
	Boolean insertAdmin(AdminSaveDTO dto);

	/**
	 * 更新用户
	 * @param dto
	 * @return
	 */
	Boolean updateAdmin(AdminSaveDTO dto);

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

	/**
	 * 获取管理员信息
	 * @param adminId
	 * @return
	 */
	Boolean deleteAdmin(Integer adminId);

	Boolean updateAdminStatus(StatusDTO dto);
}
