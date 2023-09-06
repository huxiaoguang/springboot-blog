package main.blog.mapper;

import java.util.List;
import java.util.Map;

import main.blog.dto.admin.AdminSearchDTO;
import main.blog.dto.admin.EditPassDTO;
import main.blog.dto.admin.StatusDTO;
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
	List<Admin> getAdminPage(AdminSearchDTO dto);

	/**
	 * 新增用户
	 * @param admin
	 * @return
	 */
	Boolean insertAdmin(Admin admin);

	/**
	 * 判断用户名存在
	 * @param userName
	 * @return
	 */
	Boolean existUsername(String userName);

	/**
	 * 判断手机存在
	 * @param phone
	 * @return
	 */
	Boolean existMobile(String phone);

	/**
	 * 判断邮箱存在
	 * @param email
	 * @return
	 */
	Boolean existEmail(String email);

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
	 * 账号登录֤
	 * @param login
	 * @return
	 */
	Boolean adminLogin(Map<String, Object> login);

	/**
	 * 修改账号密码
	 * @param edit
	 * @return
	 */
	Boolean editPass(Map<String, Object> edit);

	/**
	 * 删除管账号
	 * @param adminId
	 * @return
	 */
	Boolean deleteAdmin(Integer adminId);

	/**
	 * 更新账号状态
	 * @param dto
	 * @return
	 */
	Boolean updateAdminStatus(StatusDTO dto);

	/**
	 * 修改密码
	 * @param edit
	 * @return
	 */
	Boolean updatePassWord(Map<String, Object> edit);
}
