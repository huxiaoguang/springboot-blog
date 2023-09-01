package main.blog.controller.admin;

import javax.annotation.Resource;
import cn.hutool.core.util.ObjectUtil;
import main.blog.dto.admin.StatusDTO;
import main.blog.dto.admin.TagSearchDTO;
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
		if(type.equals("1"))
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
		return "admin/tag/tag-add";
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
		return "admin/tag/tag-edit";
	}

	/**
	 * 添加编辑导航操作
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "tag/save", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result save(Tag tag)
	{
		Boolean result = false;
		if(ObjectUtil.isEmpty(tag.getId()))
		{
			result = tagService.addTag(tag);
		}else {
			result = tagService.editTag(tag);
		}
		if (result)
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
