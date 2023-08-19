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

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService
{
	@Resource
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
	public Boolean addUser(User user)
	{
		return userMapper.addUser(user);
	}

	@Override
	public Boolean deleteUser(int id)
	{
		return userMapper.deleteUser(id);
	}

	@Override
	public Boolean updateUserStatus(User user)
	{
		return userMapper.updateUserStatus(user);
	}
}
