package main.blog.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.User;

public interface UserService {

	/**
	 * 获取会员信息
	 * @param id
	 * @return
	 */
	User infoUser(int id);

	/**
	 * 用户管理
	 * @param param
	 * @param page
	 * @return
	 */
	PageInfo<User> listUser(Map<String, Object> param, String page);

	/**
	 * 添加会员
	 * @param user
	 * @return
	 */
	Boolean addUser(User user);

	/**
	 * 删除会员
	 * @param id
	 * @return
	 */
	Boolean deleteUser(int id);

	/**
	 * 更新用户状态
	 * @param user
	 * @return
	 */
	Boolean updateUserStatus(User user);
}
