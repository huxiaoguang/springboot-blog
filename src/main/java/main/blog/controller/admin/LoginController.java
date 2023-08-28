package main.blog.controller.admin;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hutool.captcha.LineCaptcha;
import main.blog.utils.Result;
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

	@Resource
	private AdminService adminService;

	/**
	 * 管理员登录试图
	 * @return string
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String index() {
		return "admin/login";
	}

	/**
	 * 管理员登录操作
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "dologin", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result dologin(HttpServletRequest request, Model model)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String captcha = request.getParameter("captcha");

		// 验证码验证֤
		HttpSession validateCode = request.getSession();
		if (!captcha.equalsIgnoreCase((String) validateCode.getAttribute("captcha")))
		{
			return Result.failed("验证码错误");
		}

		Admin admin = adminService.AdminLogin(username, password);
		if (admin!=null)
		{
			model.addAttribute("admin", admin);
			return Result.success("登录成功");
		} else {
			return Result.failed("用户名或者密码错误");
		}
	}

	/**
	 * 登录验证码
	 *
	 * @return String
	 */
	@RequestMapping(value = "captcha", method = RequestMethod.GET)
	public void captcha(HttpServletResponse response, HttpSession session)
	{
		LineCaptcha lineCaptcha = cn.hutool.captcha.CaptchaUtil.createLineCaptcha(116, 36,4,10);
		session.setAttribute("captcha", lineCaptcha.getCode());
		try
		{
			ServletOutputStream outputStream = response.getOutputStream();
			lineCaptcha.write(outputStream);
			outputStream.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
