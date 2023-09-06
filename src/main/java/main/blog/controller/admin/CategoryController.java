package main.blog.controller.admin;

import java.util.List;
import javax.annotation.Resource;
import main.blog.annotation.Log;
import main.blog.dto.admin.CategorySaveDTO;
import main.blog.dto.admin.CategorySearchDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.enums.BusinessType;
import main.blog.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import main.blog.entity.Category;
import main.blog.service.CategoryService;

@Controller("Category")
@RequestMapping(value = "admin")
public class CategoryController
{
	@Resource
	private CategoryService categoryService;

	/**
	 * 栏目列表
	 * @return
	 * @return string
	 */
	@RequestMapping(value = "category/index")
	public String category()
	{
		return "admin/category/index";
	}

	/**
	 * 分类列表
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "category/data", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result data(CategorySearchDTO dto)
	{
		return Result.success(categoryService.getCategoryPage(dto));
	}

	/**
	 * 添加栏目试图
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "/category/add")
	public String add(Model model)
	{
		List<Category> list = categoryService.getCategoryList();
		model.addAttribute("list", list);
		return "admin/category/add";
	}

	/**
	 * 编辑栏目试图
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "category/edit")
	public String edit(@RequestParam(defaultValue = "0") Integer id, Model model)
	{
		if (id != 0) {
			//分类列表
			List<Category> list = categoryService.getCategoryList();
			model.addAttribute("list", list);

			//分类详情
			Category info = categoryService.detailCategory(id);
			model.addAttribute("info", info);
		}
		return "admin/category/edit";
	}

	/**
	 * 删除栏目试图
	 * @return string
	 */
	@ResponseBody
	@Log(title = "删除分类", businessType = BusinessType.DELETE)
	@RequestMapping(value = "category/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result delete(@RequestParam(defaultValue = "0") Integer id)
	{
		if (categoryService.deleteCategory(id)) {
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 新增分类
	 * @return
	 */
	@ResponseBody
	@Log(title = "新增分类", businessType = BusinessType.INSERT)
	@RequestMapping(value = "category/insert", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result insert(@Validated CategorySaveDTO dto)
	{
		if (categoryService.addCategory(dto))
		{
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 编辑分类
	 * @return
	 */
	@ResponseBody
	@Log(title = "编辑分类", businessType = BusinessType.UPDATE)
	@RequestMapping(value = "category/update", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result update(@Validated CategorySaveDTO dto)
	{
		if (categoryService.editCategory(dto))
		{
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 更新分类状态̬
	 * @return
	 */
	@ResponseBody
	@Log(title = "更新分类状态̬", businessType = BusinessType.UPDATE)
	@RequestMapping(value = "/category/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result updateStatus(StatusDTO dto)
	{
		if (categoryService.updateCategoryStatus(dto))
		{
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}
}
