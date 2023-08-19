package main.blog.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import main.blog.entity.User;
import main.blog.service.UserService;

@Controller("User")
@RequestMapping(value = "/admin")
public class UserController {

	@Resource
	private UserService userService;

	/**
	 * 会员列表
	 * @return string
	 */
	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model)
	{
		String keywords = request.getParameter("keywords");
		String cateid   = request.getParameter("cid");
		String page     = request.getParameter("page");

		Map<String, Object> param = new HashMap<String, Object>();

		// 关键词查询
		if (keywords != null && keywords != "") {
			param.put("username", keywords.trim());
			model.addAttribute("keywords", keywords);
		}

		// 分类查询
		if (cateid != null && cateid != "") {
			param.put("category_id", cateid);
			model.addAttribute("category_id", cateid);
		}

		PageInfo<User> pageinfo = userService.listUser(param, page);

		model.addAttribute("page", pageinfo);
		model.addAttribute("list", pageinfo.getList());

		return "admin/user/user";
	}

	/**
	 * 删除会员
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result delete(@RequestParam(defaultValue = "0") Integer id)
	{
		if (id == 0)
		{
			return Result.failed("参数错误");
		}

		Boolean result = userService.deleteUser(id);
		if (result) {
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 更新会员状态̬
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result updateStatus(@RequestParam(defaultValue = "0") Integer id, String status)
	{
		User user = new User();
		user.setId(id);
		user.setStatus(status);

		Boolean result = userService.updateUserStatus(user);
		if (result)
		{
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}
}
