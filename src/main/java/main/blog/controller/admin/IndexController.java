package main.blog.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import main.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.alibaba.fastjson.JSONObject;

import main.blog.entity.Admin;
import main.blog.service.AdminService;
import main.blog.service.ArticleService;
import main.blog.service.CategoryService;

@Controller("admin/Index")
@RequestMapping(value = "/admin")
public class IndexController {

	@Resource
	private ArticleService articleService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private AdminService adminService;

	/**
	 * 系统首页
	 * @return string
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model)
	{
		model.addAttribute("article",  countArticle());
		model.addAttribute("category", countCategory());
		return "admin/index";
	}

	/**
	 * 修改密码试图
	 * @return
	 */
	@RequestMapping(value = "/editPass", method = RequestMethod.GET)
	public String editPass() {
		return "admin/editpass";
	}

	/**
	 * 修改密码操作
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/editPass", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result editPass(HttpServletRequest request, Model model)
	{
		String password  = request.getParameter("password");
		String newpass   = request.getParameter("newpass");
		String renewpass = request.getParameter("renewpass");

		HttpSession session = request.getSession();
		Admin info = (Admin) session.getAttribute("admin");

		if(info==null)
		{
			return Result.failed("请重新登录");
		}

		String username = info.getUsername();
		Admin admin = adminService.AdminLogin(username, password);
		if(admin==null)
		{
			return Result.failed("原密码错误");
		}

		if (password.equals(newpass)) {
			return Result.failed("新密码不能和原密码相同");
		}

		if (!newpass.equals(renewpass)) {
			return Result.failed("两次输入的新密码不一样");
		}

		Boolean result = adminService.editPass(username, renewpass);
		if (result) {
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 统计文章数目
	 * @return Integer
	 */
	public Integer countArticle()
	{
		return articleService.countArticle();
	}

	/**
	 * 统计分类数目
	 * @return Integer
	 */
	public Integer countCategory()
	{
		return categoryService.countCategory();
	}

	/**
	 * 退出管理员登录
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/logout")
	public Result logout(HttpSession session, SessionStatus sessionStatus)
	{
		sessionStatus.setComplete();
		session.removeAttribute("user");

		if (session.getAttribute("user") == null)
		{
			return Result.success("/admin/login", "退出成功");
		} else {
			return Result.failed("退出失败");
		}
	}
}
