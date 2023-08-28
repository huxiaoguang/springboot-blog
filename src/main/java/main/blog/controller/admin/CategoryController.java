package main.blog.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import cn.hutool.core.util.ObjectUtil;
import main.blog.dto.admin.ArticleSearchDTO;
import main.blog.dto.admin.CategorySearchDTO;
import main.blog.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import main.blog.entity.Category;
import main.blog.service.ArticleService;
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
		return Result.success(categoryService.listCategory(dto));
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

		return "admin/category/category-add";
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
		return "admin/category/category-edit";
	}

	/**
	 * 删除栏目试图
	 * @return string
	 */
	@ResponseBody
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
	 * 添加编辑分类操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/category/save", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result save(Category category)
	{
		Boolean result = false;
		if(ObjectUtil.isEmpty(category.getCid()))
		{
			category.setCreateTime(new Date());
			result = categoryService.addCategory(category);
		}else {
			category.setUpdateTime(new Date());
			result = categoryService.editCategory(category);
		}
		if (result)
		{
			return Result.success("/admin/category/index", "操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 更新分类状态̬
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/category/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result updateStatus(@RequestParam(defaultValue = "0") Integer id, String status)
	{
		Category category = new Category();
		category.setCid(id);
		category.setStatus(status);
		Boolean result = categoryService.updateCategoryStatus(category);
		if (result)
		{
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}
}
