package main.blog.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.Admin;

@Repository
public interface AdminMapper {

	//获取管理员信息
	Admin adminInfo(String username);

	//管理员登录֤
	Boolean adminLogin(Map<String, Object> login);

	//修改管理员密码
	Boolean editPass(Map<String, Object> edit);
}
