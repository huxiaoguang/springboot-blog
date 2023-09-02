package main.blog.controller.admin;

import main.blog.annotation.Log;
import main.blog.dto.admin.ArticleSaveDTO;
import main.blog.dto.admin.ArticleSearchDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.entity.Article;
import main.blog.entity.Category;
import main.blog.enums.BusinessType;
import main.blog.service.ArticleService;
import main.blog.service.CategoryService;
import main.blog.utils.Result;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("Article")
@RequestMapping(value = "admin")
public class ArticleController
{
	@Resource
	private ArticleService articleService;
	@Resource
	private CategoryService categoryService;

	/**
	 * 文章视图
	 * @return string
	 */
	@RequestMapping(value = "article/index", method = RequestMethod.GET)
	public String article()
	{
		return "admin/article/index";
	}

	/**
	 * 文章列表
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "article/data", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result data(ArticleSearchDTO dto)
	{
		return Result.success(articleService.listArticle(dto));
	}

	/**
	 * 添加文章
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "article/add")
	public String add(Model model)
	{
		List<Category> list = categoryService.getCategoryList();
		model.addAttribute("list", list);
		return "admin/article/article-add";
	}

	/**
	 * 编辑文章
	 * @param model
	 * @return string
	 */
	@GetMapping("article/edit")
	public String edit(@RequestParam(defaultValue = "0") Integer id, Model model)
	{
		// 获取文章分类
		List<Category> list = categoryService.getCategoryList();
		model.addAttribute("list", list);

		// 获取文章信息
		Article info = articleService.detailArticle(id);
		model.addAttribute("info", info);
		return "admin/article/article-edit";
	}

	/**
	 * 删除文章
	 * @return string
	 */
	@ResponseBody
	@Log(title = "删除文章", businessType = BusinessType.DELETE)
	@RequestMapping(value = "article/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result<Boolean> delete(@RequestParam(defaultValue = "0") Integer id)
	{
		if (articleService.deleteArticle(id)) {
			return Result.success("操作成功");
		} else {
			return Result.success("操作失败");
		}
	}

	/**
	 * 图片上传
	 * @return
	 * @return string
	 */
	@ResponseBody
	@Log(title = "图片上传", businessType = BusinessType.UPLOAD)
	@RequestMapping(value = "article/upload", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result upload(HttpServletRequest request, @RequestParam("file") MultipartFile file)
	{
		String path = request.getSession().getServletContext().getRealPath("/upload/article/");
		String orgFilename = file.getOriginalFilename();

		// 获取图片扩展名
		String extension = FilenameUtils.getExtension(orgFilename);
		// 图片新名称
		String fileName = System.currentTimeMillis() + "." + extension;
		// 新图片绝对地址
		File targetFile = new File(path, fileName);

		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 上传图片
		try {
			file.transferTo(targetFile);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("savepath", request.getContextPath() + "/upload/article/");
			data.put("savename", request.getContextPath() + "/upload/article/" + fileName);
			data.put("filename", orgFilename);

			return Result.success(data, "上传成功");
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failed("上传失败");
		}
	}

	/**
	 * 新增文章
	 * @return json
	 */
	@ResponseBody
	@Log(title = "新增文章", businessType = BusinessType.INSERT)
	@RequestMapping(value = "article/insert", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result insert(@Validated ArticleSaveDTO dto)
	{
		if (articleService.addArticle(dto))
		{
			return Result.success("/admin/article/index", "操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 编辑文章
	 * @return json
	 */
	@ResponseBody
	@Log(title = "编辑文章", businessType = BusinessType.UPDATE)
	@RequestMapping(value = "article/update", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result update(@Validated ArticleSaveDTO dto)
	{
		if (articleService.editArticle(dto))
		{
			return Result.success("/admin/article/index", "操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 更新文章状态
	 * @return
	 */
	@ResponseBody
	@Log(title = "更新文章状态", businessType = BusinessType.UPDATE)
	@RequestMapping(value = "article/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result updateStatus(StatusDTO dto)
	{
		Boolean result = articleService.updateArticleStatus(dto);
		if (result)
		{
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}
}
