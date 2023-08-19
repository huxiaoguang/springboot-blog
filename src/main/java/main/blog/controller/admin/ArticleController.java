package main.blog.controller.admin;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import main.blog.entity.Article;
import main.blog.entity.Category;
import main.blog.service.ArticleService;
import main.blog.service.CategoryService;

@Controller("Article")
@RequestMapping(value = "/admin")
public class ArticleController {

	@Resource
	private ArticleService articleService;
	@Resource
	private CategoryService categoryService;

	/**
	 * 文章列表
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "/article/index")
	public String article(HttpServletRequest request, Model model) throws Exception
	{
		String keywords = request.getParameter("keywords");
		String cateid   = request.getParameter("cid");
		String page     = request.getParameter("page");

		Map<String, Object> param = new HashMap<String, Object>();

		// 关键词查询
		if (keywords != null && keywords != "") {
			param.put("title", keywords.trim());
			model.addAttribute("keywords", keywords);
		}

		// 分类查询
		if (cateid != null && cateid != "") {
			param.put("category_id", cateid);
			model.addAttribute("category_id", cateid);
		}

		PageInfo<Article> pageinfo = articleService.listArticle(param, page);

		model.addAttribute("page", pageinfo);
		model.addAttribute("list", pageinfo.getList());

		return "admin/article/article";
	}

	/**
	 * 添加文章
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "/article/add")
	public String add(Model model)  throws Exception
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
	@RequestMapping(value = "/article/edit")
	public String edit(@RequestParam(defaultValue = "0") Integer id, Model model) throws Exception
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
	 * @param model
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "/article/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject delete(@RequestParam(defaultValue = "0") Integer id) throws Exception
	{
		JSONObject json = new JSONObject();

		if (id == 0)
		{
			json.put("status", 0);
			json.put("msg", "参数错误");
			return json;
		}

		boolean result = articleService.deleteArticle(id);

		if (result) {
			json.put("status", 1);
			json.put("msg", "操作成功");
		} else {
			json.put("status", 0);
			json.put("msg", "操作失败");
		}
		return json;
	}

	/**
	 * 图片上传
	 * @param model
	 * @return
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "/article/upload", method = RequestMethod.POST)
	public JSONObject upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		JSONObject json = new JSONObject();
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

			json.put("status", 1);
			json.put("msg", "上传成功");
			json.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();

			json.put("status", 0);
			json.put("msg", "上传失败");
		}

		return json;
	}

	/**
	 * 添加编辑文章操作
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/article/save", method = RequestMethod.POST, headers = "Accept=application/json")
	public  JSONObject save(@Valid Article article, BindingResult msg) throws Exception
	{
		JSONObject json = new JSONObject();

		//错误提示
		if(msg.hasErrors())
		{
			json.put("status", 0);
			json.put("msg",  msg.getFieldError().getDefaultMessage());
			return json;
        }

		article.setUpdateTime(new Date());
		boolean result = false;

		if(article.getId()==0)
		{
			result = articleService.addArticle(article);
		}else {
			result = articleService.editArticle(article);
		}

		if (result)
		{
			json.put("status", 1);
			json.put("msg", "操作成功");
			json.put("url", "/admin/article/index");
		} else {
			json.put("status", 0);
			json.put("msg", "操作失败");
		}
		return json;
	}

	/**
	 * 文章状态更新
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/article/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject updateStatus(@RequestParam(defaultValue = "0") Integer id, String status) throws Exception
	{
		JSONObject json = new JSONObject();

		Article article = new Article();

		article.setId(id);
		article.setStatus(status);

		boolean result = articleService.updateArticleStatus(article);

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
