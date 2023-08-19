package main.blog.controller.admin;

import java.util.Date;
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

import main.blog.entity.Link;
import main.blog.service.LinkService;

@Controller("Link")
@RequestMapping(value = "/admin")
public class LinkController {

	@Resource
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
	public Result save(Link link)
	{
		Boolean result = false;
		if(link.getId()==0)
		{
			link.setCreateTime(new Date());
			result = linkService.addLink(link);
		}else {
			result = linkService.editLink(link);
		}

		if (result)
		{
			return Result.success("/admin/link/index","操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 删除友链操作
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "/link/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result delete(@RequestParam(defaultValue = "0") Integer id)
	{
		if (id == 0) {
			return Result.failed("参数错误");
		}

		Boolean result = linkService.deleteLink(id);
		if (result) {
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 更新友链状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/link/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result updateStatus(@RequestParam(defaultValue = "0") Integer id, String status)
	{
		Link link = new Link();
		link.setId(id);
		link.setStatus(status);

		Boolean result = linkService.updateLinkStatus(link);
		if (result)
		{
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}
}
