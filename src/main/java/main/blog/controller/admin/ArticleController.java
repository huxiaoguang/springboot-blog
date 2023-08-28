package main.blog.controller.admin;

import main.blog.dto.admin.ArticleDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.entity.Article;
import main.blog.entity.Category;
import main.blog.service.ArticleService;
import main.blog.service.CategoryService;
import main.blog.utils.Result;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
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
	public Result data(ArticleDTO dto)
	{
		return Result.success(articleService.listArticle(dto));
	}

	/**
	 * 添加文章
	 * @param model
	 * @return string
	 */
	@GetMapping("article/add")
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
	 * 删除文章操作
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "article/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result<Boolean> delete(@RequestParam(defaultValue = "0") Integer id)
	{
		if (id == 0) {
			return Result.failed("参数错误");
		}

		boolean result = articleService.deleteArticle(id);
		if (result) {
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
	@PostMapping("article/upload")
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

			return Result.success(data, "上传失败");
		} catch (Exception e) {
			e.printStackTrace();
			return Result.failed("上传失败");
		}
	}

	/**
	 * 添加编辑文章操作
	 * @return json
	 */
	@PostMapping("article/save")
	public Result save(@Valid Article article, BindingResult msg)
	{
		// 错误提示
		if(msg.hasErrors())
		{
			return Result.failed( msg.getFieldError().getDefaultMessage());
        }

		boolean result = false;
		if(article.getId()==0)
		{
			result = articleService.addArticle(article);
		}else {
			article.setUpdateTime(new Date());
			result = articleService.editArticle(article);
		}
		if (result)
		{
			return Result.success("/admin/article/index", "操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}

	/**
	 * 文章状态更新
	 * @return
	 */
	@ResponseBody
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
