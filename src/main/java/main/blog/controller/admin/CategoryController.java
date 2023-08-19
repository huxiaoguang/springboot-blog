package main.blog.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import main.blog.entity.Category;
import main.blog.service.ArticleService;
import main.blog.service.CategoryService;

@Controller("Category")
@RequestMapping(value = "/admin")
public class CategoryController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * 栏目列表
	 * @param model
	 * @return
	 * @return string
	 */
	@RequestMapping(value = "/category/index")
	public  String category(HttpServletRequest request, Model model)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		String keywords = request.getParameter("keywords");

		// 按关键词查询
		if (keywords != null && keywords != "") {
			param.put("name", keywords.trim());
			model.addAttribute("keywords", keywords);
		}

		List<Category> list = categoryService.listCategory(param);
		model.addAttribute("list", list);

		return "admin/category/category";
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
	@RequestMapping(value = "/category/edit")
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
	@RequestMapping(value = "/category/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject delete(@RequestParam(defaultValue = "0") Integer id, HttpServletRequest request)
	{
		JSONObject json = new JSONObject();
		if (id == 0) {
			json.put("status", 0);
			json.put("msg", "参数错误");
			return json;
		}

		int countSubCate = articleService.countSubCategory(id);
		if (countSubCate > 0)
		{
			json.put("status", 0);
			json.put("msg", "该分类下有子分类，不能被删除");
			return json;
		}

		int countCateArticle = articleService.countCategoryArticle(id);
		if (countCateArticle > 0)
		{
			json.put("status", 0);
			json.put("msg", "该分类下有文章，不能被删除");
			return json;
		}

		boolean result = categoryService.deleteCategory(id);
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

	/**
	 * 添加编辑分类操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/category/save", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject save(Category category)
	{
		JSONObject json = new JSONObject();
		boolean result = false;
		category.setUpdateTime(new Date());

		if(category.getId()==0)
		{
			result = categoryService.addCategory(category);
		}else {
			result = categoryService.editCategory(category);
		}

		if (result)
		{
			json.put("status", 1);
			json.put("msg", "操作成功");
			json.put("url", "/admin/category/index");
		} else {
			json.put("status", 0);
			json.put("msg", "操作失败");
		}
		return json;
	}

	/**
	 * 更新分类状态̬
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/category/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject updateStatus(@RequestParam(defaultValue = "0") Integer id, String status)
	{
		JSONObject json = new JSONObject();

		Category category = new Category();

		category.setId(id);
		category.setStatus(status);

		boolean result = categoryService.updateCategoryStatus(category);

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
