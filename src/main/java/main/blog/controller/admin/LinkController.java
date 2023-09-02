package main.blog.controller.admin;

import main.blog.annotation.Log;
import main.blog.dto.admin.LinkSearchDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.entity.Link;
import main.blog.enums.BusinessType;
import main.blog.service.LinkService;
import main.blog.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller("Link")
@RequestMapping(value = "/admin")
public class LinkController {

	@Resource
	private LinkService linkService;

	/**
	 * 友情链接列表
	 * @return string
	 */
	@RequestMapping(value = "link/index", method = RequestMethod.GET)
	public String index()
	{
		return "admin/link/link";
	}

	/**
	 * 云标签列表
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "link/data", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result data(LinkSearchDTO dto)
	{
		return Result.success(linkService.listLink(dto));
	}

	/**
	 * 添加友情链接试图
	 * @return string
	 */
	@RequestMapping(value = "link/add", method = RequestMethod.GET)
	public String add()
	{
		return "admin/link/link-add";
	}

	/**
	 * 编辑友情链接试图
	 * @return string
	 */
	@RequestMapping(value = "link/edit", method = RequestMethod.GET)
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
	 * 添加友链
	 * @return
	 */
	@ResponseBody
	@Log(title = "添加友链", businessType = BusinessType.INSERT)
	@RequestMapping(value = "link/insert", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result insert(Link link)
	{
		if (linkService.addLink(link))
		{
			return Result.success("/admin/link/index","操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 编辑友链
	 * @return
	 */
	@ResponseBody
	@Log(title = "编辑友链", businessType = BusinessType.UPDATE)
	@RequestMapping(value = "link/update", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result update(Link link)
	{
		if (linkService.editLink(link))
		{
			return Result.success("/admin/link/index","操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 删除友链
	 * @return string
	 */
	@ResponseBody
	@Log(title = "删除友链", businessType = BusinessType.DELETE)
	@RequestMapping(value = "link/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result delete(@RequestParam(defaultValue = "0") Integer id)
	{
		if (linkService.deleteLink(id)) {
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
	@Log(title = "更新友链状态", businessType = BusinessType.UPDATE)
	@RequestMapping(value = "link/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result updateStatus(StatusDTO dto)
	{
		if (linkService.updateLinkStatus(dto))
		{
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}
}
