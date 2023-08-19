package main.blog.controller.admin;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import main.blog.entity.AdminLog;
import main.blog.service.AdminLogService;

@Controller("AdminLog")
@RequestMapping(value = "/admin")
public class AdminLogController
{
	@Resource
	private AdminLogService adminLogService;

	/**
	 * 管理员日志
	 * @return string
	 */
	@RequestMapping(value = "/logs/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) throws Exception
	{
		Map<String, Object> param = new HashMap<String, Object>();

		String keywords = request.getParameter("keywords");
		String ip = request.getParameter("ip");
		String username = request.getParameter("username");
		String page = request.getParameter("page");

		// 关键词搜索
		if (keywords != null && keywords != "") {
			param.put("title", keywords.trim());
			model.addAttribute("keywords", keywords);
		}
		// IP地址搜索
		if (ip != null && ip != "") {
			param.put("ip", ip.trim());
			model.addAttribute("ip", ip);
		}
		// 用户名搜索
		if (username != null && username != "") {
			param.put("username", username.trim());
			model.addAttribute("username", username);
		}

		PageInfo<AdminLog> pageinfo = adminLogService.listAdminLog(param, page);

		model.addAttribute("page", pageinfo);
		model.addAttribute("list", pageinfo.getList());

		return "admin/logs/log";
	}

	/**
	 * 删除管理员日志
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "/logs/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject delete(@RequestParam(defaultValue = "0") Integer id) throws Exception
	{
		JSONObject json = new JSONObject();
		if (id == 0)
		{
			json.put("status", 0);
			json.put("msg", "参数错误");
			return json;
		}

		boolean result = adminLogService.deleteAdminLog(id);
		if (result) {
			json.put("status", 1);
			json.put("msg", "操作成功");
		} else {
			json.put("status", 0);
			json.put("msg", "操作失败");
		}
		return json;
	}
}
