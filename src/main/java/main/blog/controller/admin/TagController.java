package main.blog.controller.admin;

import javax.annotation.Resource;

import main.blog.annotation.Log;
import main.blog.dto.admin.StatusDTO;
import main.blog.dto.admin.TagSearchDTO;
import main.blog.enums.BusinessType;
import main.blog.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@RequestMapping(value = "tag/index", method = RequestMethod.GET)
	public String index(@RequestParam(defaultValue = "0") Integer type)
	{
		if(type.equals(1))
		{
			return "admin/tag/tagbox";
		}else {
			return "admin/tag/index";
		}
	}

	/**
	 * 云标签列表
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "tag/data", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result data(TagSearchDTO dto)
	{
		return Result.success(tagService.listTag(dto));
	}

	/**
	 * 添加标签
	 * @return string
	 */
	@RequestMapping(value = "tag/add", method = RequestMethod.GET)
	public String add() {
		return "admin/tag/add";
	}

	/**
	 * 编辑标签
	 * @return string
	 */
	@RequestMapping(value = "tag/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(defaultValue = "0") Integer id, Model model)
	{
		if (id!=0)
		{
			Tag info = tagService.infoTag(id);
			model.addAttribute("info", info);
		}
		return "admin/tag/edit";
	}

	/**
	 * 添加标签
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@Log(title = "添加标签", businessType = BusinessType.INSERT)
	@RequestMapping(value = "tag/insert", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result insert(Tag tag)
	{
		if (tagService.addTag(tag))
		{
			return Result.success("/admin/tag/index", "操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 编辑标签
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@Log(title = "编辑标签", businessType = BusinessType.UPDATE)
	@RequestMapping(value = "tag/update", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result update(Tag tag)
	{
		if (tagService.editTag(tag))
		{
			return Result.success("/admin/tag/index", "操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 删除标签
	 * @return string
	 */
	@ResponseBody
	@Log(title = "删除标签", businessType = BusinessType.DELETE)
	@RequestMapping(value = "tag/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result delete(@RequestParam(defaultValue = "0") Integer id)
	{
		if (tagService.deleteTag(id)) {
			return Result.success("删除成功");
		} else {
			return Result.failed("删除失败");
		}
	}

	/**
	 * 更新标签状态
	 * @return
	 */
	@ResponseBody
	@Log(title = "更新标签状态", businessType = BusinessType.DELETE)
	@RequestMapping(value = "tag/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result updateStatus(StatusDTO dto)
	{
		if (tagService.updateTagStatus(dto))
		{
			return Result.success("操作成功");
		} else {
			return Result.failed("删除失败");
		}
	}
}
