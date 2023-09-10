package main.blog.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import main.blog.dto.admin.EditProfilePassDTO;
import main.blog.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import main.blog.service.AdminService;
import main.blog.service.ArticleService;
import main.blog.service.CategoryService;

@Controller("admin/Index")
@RequestMapping(value = "/admin")
public class IndexController {

	@Resource
	private HttpSession session;
	@Resource
	private AdminService adminService;
	@Resource
	private ArticleService articleService;
	@Resource
	private CategoryService categoryService;

	/**
	 * 系统首页
	 * @return string
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index()
	{
		return "admin/layout";
	}

	/**
	 * 系统首页
	 * @return string
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String console(Model model)
	{
		model.addAttribute("article",  countArticle());
		model.addAttribute("category", countCategory());
		return "admin/index/index";
	}

	/**
	 * 修改密码试图
	 * @return
	 */
	@RequestMapping(value = "editPass", method = RequestMethod.GET)
	public String editPass() {
		return "admin/editPass";
	}

	/**
	 * 修改密码操作
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "editPass", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result editPass(EditProfilePassDTO dto, SessionStatus sessionStatus)
	{
		if (adminService.editPass(dto))
		{
			return Result.success("修改成功，请重新登录！");
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
	@RequestMapping("logout")
	public Result logout(SessionStatus sessionStatus)
	{
		sessionStatus.setComplete();
		session.removeAttribute("admin");

		if (session.getAttribute("admin") == null)
		{
			return Result.success("/admin/login", "退出成功");
		} else {
			return Result.failed("退出失败");
		}
	}
}
