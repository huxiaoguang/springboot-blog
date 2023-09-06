package main.blog.mapper;

import java.util.List;
import java.util.Map;

import main.blog.dto.admin.AdminSearchDTO;
import org.springframework.stereotype.Repository;

import main.blog.entity.Admin;

@Repository
public interface AdminMapper
{
	/**
	 * 用户列表
	 * @param dto
	 * @return
	 */
	List<Admin> getAdminList(AdminSearchDTO dto);

	/**
	 * 新增用户
	 * @param admin
	 * @return
	 */
	Boolean insertAdmin(Admin admin);

	/**
	 * 更新用户信息
	 * @param admin
	 * @return
	 */
	Boolean updateAdmin(Admin admin);

	/**
	 * 获取管理员信息
	 * @param adminId
	 * @return
	 */
	Admin getAdminInfo(Integer adminId);

	/**
	 * 获取账号信息
	 * @param username
	 * @return
	 */
	Admin getAdminByUsername(String username);

	/**
	 * 管理员登录֤
	 * @param login
	 * @return
	 */
	Boolean adminLogin(Map<String, Object> login);

	/**
	 * 修改管理员密码
	 * @param edit
	 * @return
	 */
	Boolean editPass(Map<String, Object> edit);

	/**
	 * 删除管理员
	 * @param adminId
	 * @return
	 */
	Boolean deleteAdmin(Integer adminId);
}
