package main.blog.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSONObject;

import main.blog.entity.Admin;
import main.blog.service.AdminService;
import main.blog.utils.CaptchaUtil;

@Controller("Login")
@SessionAttributes("admin")
@RequestMapping(value = "/admin")
public class LoginController {

	@Autowired
	private AdminService adminService;//自动装载Service接口

	/**
	 * 管理员登录试图
	 *
	 * @return string
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index() {
		return "admin/login";
	}

	/**
	 * 管理员登录操作
	 *
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/dologin", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject dologin(HttpServletRequest request, Model model)
	{
		JSONObject json = new JSONObject();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String code = request.getParameter("code");

		// 验证码验证֤
		HttpSession validateCode = request.getSession();

		if (!code.equalsIgnoreCase((String) validateCode.getAttribute("validateCode"))) {
			json.put("status", 2);
			json.put("msg", "验证码错误");
			return json;
		}

		Admin admin = adminService.AdminLogin(username, password);

		if (admin!=null)
		{
			model.addAttribute("admin", admin);
			json.put("status", 1);
			json.put("msg", "登录成功");
		} else {
			json.put("status", 0);
			json.put("msg", "用户名或者密码错误");
		}

		return json;
	}

	/**
	 * 登录验证码
	 *
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CaptchaUtil code = new CaptchaUtil(100, 38, 5, 50, 22, "validateCode");
		code.getCode(request, response);
	}
}
