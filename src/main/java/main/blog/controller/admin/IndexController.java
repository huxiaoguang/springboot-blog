package main.blog.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public JSONObject editPass(HttpServletRequest request, Model model)
	{
		JSONObject json = new JSONObject();

		String password  = request.getParameter("password");
		String newpass   = request.getParameter("newpass");
		String renewpass = request.getParameter("renewpass");

		HttpSession session = request.getSession();
		Admin info = (Admin) session.getAttribute("admin");

		if(info!=null)
		{
			String username = info.getUsername();
			Admin admin = adminService.AdminLogin(username, password);

			if(admin==null)
			{
				json.put("status", 0);
				json.put("msg", "原密码错误");
				return json;
			}

			if (password.equals(newpass)) {
				json.put("status", 0);
				json.put("msg", "新密码不能和原密码相同");
				return json;
			}

			if (!newpass.equals(renewpass)) {
				json.put("status", 0);
				json.put("msg", "两次输入的新密码不一样");
				return json;
			}

			boolean result = adminService.editPass(username, renewpass);

			if (result) {
				json.put("status", 1);
				json.put("msg", "操作成功");
			} else {
				json.put("status", 0);
				json.put("msg", "操作失败");
			}
		}

		return json;
	}

	/**
	 * 统计文章数目
	 * @return Integer
	 */
	public Integer countArticle()
	{
		int count = articleService.countArticle();
		return count;
	}

	/**
	 * 统计分类数目
	 * @return Integer
	 */
	public Integer countCategory()
	{
		int count = categoryService.countCategory();
		return count;
	}

	/**
	 * 退出管理员登录
	 *
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/logout")
	public JSONObject logout(HttpSession session, SessionStatus sessionStatus) {
		JSONObject json = new JSONObject();
		sessionStatus.setComplete();
		session.removeAttribute("user");

		if (session.getAttribute("user") == null) {
			json.put("status", 1);
			json.put("msg", "退出成功");
			json.put("url", "/admin/login");
		} else {
			json.put("status", 0);
			json.put("msg", "退出失败");
		}
		return json;
	}
}
