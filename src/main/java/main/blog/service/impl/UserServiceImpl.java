package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import main.blog.entity.User;
import main.blog.mapper.UserMapper;
import main.blog.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserMapper userMapper;

	@Override
	public User infoUser(int id)
	{
		User userinfo = userMapper.infoUser(id);
		return userinfo;
	}

	@Override
	public PageInfo<User> listUser(Map<String, Object> param, String page)
	{
		int P = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);

		PageHelper.startPage(P, 15);
		List<User> list = userMapper.listUser(param);
		PageInfo<User> pageinfo = new PageInfo<User>(list);

		return pageinfo;
	}

	@Override
	public boolean addUser(User user)
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
	public boolean updateUserStatus(User user)
	{
		boolean result = userMapper.updateUserStatus(user);
		return 	result;
	}
}
