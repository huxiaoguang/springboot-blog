package main.blog.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.AdminBean;

@Repository
public interface AdminMapper {
	
	//获取管理员信息
	public AdminBean adminInfo(String username);
	
	//管理员登录֤
	public boolean adminLogin(Map<String, Object> login);
	
	//修改管理员密码
	public boolean editPass(Map<String, Object> edit);
}
