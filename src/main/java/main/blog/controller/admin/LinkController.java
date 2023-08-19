package main.blog.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import main.blog.entity.Link;
import main.blog.service.LinkService;

@Controller("Link")
@RequestMapping(value = "/admin")
public class LinkController {

	@Autowired
	private LinkService linkService;

	/**
	 * 友情链接列表
	 * @return string
	 */
	@RequestMapping(value = "/link/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model)
	{
		String keywords = request.getParameter("keywords");
		String page     = request.getParameter("page");

		Map<String, Object> param = new HashMap<String, Object>();

		// 关键词查询
		if (keywords != null && keywords != "") {
			param.put("name", keywords.trim());
			model.addAttribute("keywords", keywords);
		}

		PageInfo<Link> pageinfo = linkService.listLink(param, page);

		model.addAttribute("page", pageinfo);
		model.addAttribute("list", pageinfo.getList());

		return "admin/link/link";
	}

	/**
	 * 添加友情链接试图
	 * @return string
	 */
	@RequestMapping(value = "/link/add", method = RequestMethod.GET)
	public String add()
	{
		return "admin/link/link-add";
	}

	/**
	 * 编辑友情链接试图
	 * @return string
	 */
	@RequestMapping(value = "/link/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(defaultValue = "0") Integer id, Model model)
	{
		if (id != 0)
		{
			Link info = linkService.infoLink(id);
			model.addAttribute("info", info);
		}
		return "admin/link/link-edit";
	}

	/**
	 * 添加编辑友链操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/link/save", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject save(Link link)
	{
		JSONObject json = new JSONObject();
		link.setCreateTime(new Date());

		boolean result = false;

		if(link.getId()==0)
		{
			result = linkService.addLink(link);
		}else {
			result = linkService.editLink(link);
		}

		if (result)
		{
			json.put("status", 1);
			json.put("msg", "操作成功");
			json.put("url", "/admin/link/index");
		} else {
			json.put("status", 0);
			json.put("msg", "操作失败");
		}
		return json;
	}

	/**
	 * 删除友链操作
	 * @param model
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "/link/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject delete(@RequestParam(defaultValue = "0") Integer id)
	{
		JSONObject json = new JSONObject();

		if (id == 0)
		{
			json.put("status", 0);
			json.put("msg", "参数错误");
			return json;
		}

		boolean result = linkService.deleteLink(id);

		if (result) {
			json.put("status", 1);
			json.put("msg", "操作成功");
		} else {
			json.put("status", 0);
			json.put("msg", "操作失败");
		}
		return json;
	}

	/**
	 * 更新友链状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/link/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject updateStatus(@RequestParam(defaultValue = "0") Integer id, String status)
	{
		JSONObject json = new JSONObject();

		Link link = new Link();
		link.setId(id);
		link.setStatus(status);

		boolean result = linkService.updateLinkStatus(link);

		if (result)
		{
			json.put("status", 1);
			json.put("msg", "操作成功");
		} else {
			json.put("status", 0);
			json.put("msg", "操作失败");
		}
		return json;
	}
}
