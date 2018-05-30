package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import main.blog.entity.UserBean;
import main.blog.mapper.UserMapper;
import main.blog.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserBean infoUser(int id) 
	{
		UserBean userinfo = userMapper.infoUser(id);
		return userinfo;
	}
	
	@Override
	public PageInfo<UserBean> listUser(Map<String, Object> param, String page) 
	{	
		int P = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);
		
		PageHelper.startPage(P, 15);
		List<UserBean> list = userMapper.listUser(param);
		PageInfo<UserBean> pageinfo = new PageInfo<UserBean>(list);
		
		return pageinfo;
	}
	
	@Override
	public boolean addUser(UserBean user) 
	{
		boolean result = userMapper.addUser(user);
		return 	result;
	}

	@Override
	public boolean deleteUser(int id) 
	{
		boolean result = userMapper.deleteUser(id);
		return 	result;
	}

	@Override
	public boolean updateUserStatus(UserBean user) 
	{
		boolean result = userMapper.updateUserStatus(user);
		return 	result;
	}
}
