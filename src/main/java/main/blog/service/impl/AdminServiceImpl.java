package main.blog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.AdminSaveDTO;
import main.blog.dto.admin.EditPassDTO;
import main.blog.dto.admin.AdminSearchDTO;
import main.blog.utils.RandomUtil;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import main.blog.entity.Admin;
import main.blog.mapper.AdminMapper;
import main.blog.service.AdminService;
import main.blog.utils.Md5Util;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service("adminService")
public class AdminServiceImpl implements AdminService
{
	@Resource
	private AdminMapper adminMapper;
	@Resource
	private HttpServletRequest request;
	@Resource
	private HttpSession session;

	@Override
	public PageInfo<Admin> getAdminList(AdminSearchDTO dto)
	{
		PageHelper.startPage(dto.getPage(), dto.getLimit());
		List<Admin> list = adminMapper.getAdminList(dto);
		return new PageInfo<>(list);
	}

	@Override
	public Boolean insertAdmin(AdminSaveDTO dto)
	{
		Admin admin = (Admin) session.getAttribute("admin");
		Admin user = new Admin();
		BeanCopier.create(AdminSaveDTO.class, Admin.class, false).copy(dto, user,  null);

		String salt = RandomUtil.generateString(6);
		user.setSalt(salt);
		user.setPassword(Md5Util.md5(Md5Util.md5(dto.getPassword()) + salt));
		user.setCreateBy(admin.getUsername());
		user.setUpdateBy(admin.getUsername());
		return adminMapper.insertAdmin(user);
	}

	@Override
	public Boolean updateAdmin(AdminSaveDTO dto)
	{
		Admin admin = (Admin) session.getAttribute("admin");
		Admin user = new Admin();
		BeanCopier.create(AdminSaveDTO.class, Admin.class, false).copy(dto, user,  null);
		user.setUpdateBy(admin.getUsername());
		return adminMapper.updateAdmin(user);
	}

	@Override
	public Admin AdminLogin(String username, String password)
	{
		Admin admin = null;
		if (username != null)
		{
			Admin info = this.getAdminByUsername(username);
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
	public Admin getAdminInfo(Integer adminId)
	{
		return adminMapper.getAdminInfo(adminId);
	}

	@Override
	public Admin getAdminByUsername(String username) {
		Admin info = null;

		if (username != null) {
			info = adminMapper.getAdminByUsername(username);
		}
		return info;
	}

	@Override
	public Boolean editPass(EditPassDTO dto)
	{
		HttpSession session = request.getSession();
		Admin info = (Admin) session.getAttribute("admin");

		if(info==null) {
			throw new RuntimeException("请重新登录");
		}

		String username = info.getUsername();
		Admin admin = this.AdminLogin(username, dto.getPassword());
		if(admin==null) {
			throw new RuntimeException("原密码错误");
		}

		if (dto.getPassword().equals(dto.getNewpass())) {
			throw new RuntimeException("新密码不能和原密码相同");
		}

		if (!dto.getNewpass().equals(dto.getRenewpass())) {
			throw new RuntimeException("两次输入的新密码不一样");
		}

		// 修改密码
		String salt = RandomUtil.generateString(6);
		Map<String, Object> edit = new HashMap<String, Object>();

		edit.put("salt", salt);
		edit.put("username", username);
		edit.put("password", Md5Util.md5(Md5Util.md5(dto.getNewpass()) + salt));

		Boolean result = adminMapper.editPass(edit);
		if(result)
		{
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean deleteAdmin(Integer adminId)
	{
		if (adminId.equals(1))
		{
			throw new RuntimeException("超级管理员不能删除");
		}
		return adminMapper.deleteAdmin(adminId);
	}
}
