package main.blog.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
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

import main.blog.entity.Tag;
import main.blog.service.TagService;

@Controller("Tag")
@RequestMapping(value = "/admin")
public class TagController {

	@Resource
	private TagService tagService;

	/**
	 * 云标签列表
	 * @return string
	 */
	@RequestMapping(value = "/tag/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model)
	{
		String keywords = request.getParameter("keywords");
		String page     = request.getParameter("page");
		String type  	= request.getParameter("type");

		Map<String, Object> param = new HashMap<String, Object>();

		// 关键词搜索
		if (keywords != null && keywords != "")
		{
			param.put("tagname", keywords.trim());
			model.addAttribute("keywords", keywords);
		}

		PageInfo<Tag> pageinfo = tagService.listTag(param, page);

		model.addAttribute("page", pageinfo);
		model.addAttribute("list", pageinfo.getList());

		if(type==null)
		{
			type = "0";
		}

		if(type.equals("1"))
		{
			return "admin/tag/tagbox";
		}else {
			return "admin/tag/tag";
		}
	}

	/**
	 * 添加标签
	 * @return string
	 */
	@RequestMapping(value = "/tag/add", method = RequestMethod.GET)
	public String add() {
		return "admin/tag/tag-add";
	}

	/**
	 * 编辑标签
	 * @return string
	 */
	@RequestMapping(value = "/tag/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(defaultValue = "0") Integer id, Model model)
	{
		if (id!=0)
		{
			Tag info = tagService.infoTag(id);
			model.addAttribute("info", info);
		}

		return "admin/tag/tag-edit";
	}

	/**
	 * 添加编辑导航操作
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/tag/save", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject save(Tag tag)
	{
		JSONObject json = new JSONObject();
		boolean result = false;

		if(tag.getId()==0)
		{
			result = tagService.addTag(tag);
		}else {
			result = tagService.editTag(tag);
		}

		if (result)
		{
			json.put("status", 1);
			json.put("msg", "操作成功");
			json.put("url", "/admin/tag/index");
		} else {
			json.put("status", 0);
			json.put("msg", "操作失败");
		}

		return json;
	}

	/**
	 * 删除标签
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "/tag/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject delete(@RequestParam(defaultValue = "0") Integer id)
	{
		JSONObject json = new JSONObject();
		if (id == 0) {
			json.put("status", 0);
			json.put("msg", "参数错误");
			return json;
		}

		Boolean result = tagService.deleteTag(id);
		if (result) {
			json.put("status", 1);
			json.put("msg", "删除成功");
		} else {
			json.put("status", 0);
			json.put("msg", "删除失败");
		}
		return json;
	}

	/**
	 * 更新标签状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/tag/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject updateStatus(@RequestParam(defaultValue = "0") Integer id, String status)
	{
		JSONObject json = new JSONObject();

		Tag tag = new Tag();
		tag.setId(id);
		tag.setStatus(status);

		Boolean result = tagService.updateTagStatus(tag);
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
