package main.blog.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.User;

public interface UserService {

	//获取会员信息
	public User infoUser(int id);

	//用户管理
	public PageInfo<User> listUser(Map<String, Object> param, String page);

	//添加会员
	public boolean addUser(User user);

	//删除会员
	public boolean deleteUser(int id);

	//更新用户状态
	public boolean updateUserStatus(User user);
}
