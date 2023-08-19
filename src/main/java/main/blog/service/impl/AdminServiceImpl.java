package main.blog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.blog.entity.Admin;
import main.blog.mapper.AdminMapper;
import main.blog.service.AdminService;
import main.blog.utils.Md5Util;
import main.blog.utils.RandomUtil;

import javax.annotation.Resource;

@Service("adminService")
public class AdminServiceImpl implements AdminService
{
	@Resource
	private AdminMapper adminMapper;

	@Override
	public Admin AdminLogin(String username, String password)
	{
		Admin admin = null;

		if (username != null) {

			Admin info = this.adminInfo(username);
			String salt = info.getSalt();

			if (salt != null && salt != "") {
				password = Md5Util.md5(Md5Util.md5(password) + salt);
				if (this.AuthAdmin(username, password)) {
					admin = info;
				}
			}
		}

		return admin;
	}

	@Override
	public Boolean AuthAdmin(String username, String password) {
		Boolean result = false;

		Map<String, Object> login = new HashMap<String, Object>();
		login.put("username", username);
		login.put("password", password);

		if (adminMapper.adminLogin(login)) {
			result = true;
		}
		return result;
	}

	@Override
	public Admin adminInfo(String username) {
		Admin info = null;

		if (username != null) {
			info = adminMapper.adminInfo(username);
		}
		return info;
	}

	@Override
	public Boolean editPass(String username, String newpass)
	{
		//修改密码
		String salt = RandomUtil.generateString(6);
		Map<String, Object> edit = new HashMap<String, Object>();

		edit.put("salt", salt);
		edit.put("username", username);
		edit.put("password", Md5Util.md5(Md5Util.md5(newpass) + salt));

		Boolean result = adminMapper.editPass(edit);
		if(result)
		{
			return true;
		}else {
			return false;
		}
	}
}
