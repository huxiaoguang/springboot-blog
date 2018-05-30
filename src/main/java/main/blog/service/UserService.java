package main.blog.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.UserBean;

public interface UserService {
	
	//获取会员信息
	public UserBean infoUser(int id);
	
	//用户管理
	public PageInfo<UserBean> listUser(Map<String, Object> param, String page);
	
	//添加会员
	public boolean addUser(UserBean user);
	
	//删除会员
	public boolean deleteUser(int id);
	
	//更新用户状态
	public boolean updateUserStatus(UserBean user);
}