package main.blog.service;

import main.blog.entity.AdminBean;

public interface AdminService {
	
	//获取管理员信息
	public AdminBean adminInfo(String username);
	
	//验证用户名和密码
	public boolean AuthAdmin(String username, String password);
	
	//用户登录接口
	public AdminBean AdminLogin(String username, String password);
	
	//修改密码接口
	public boolean editPass(String username, String newpass);
}
