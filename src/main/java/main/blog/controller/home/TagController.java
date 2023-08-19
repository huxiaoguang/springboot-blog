package main.blog.controller.home;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;

import main.blog.entity.Article;
import main.blog.entity.Tag;
import main.blog.service.ArticleService;
import main.blog.service.TagService;

@Controller("home/Tag")
public class TagController extends HomeController{

	@Autowired
	private ArticleService articleService;//自动装载Service接口

	@Autowired
	private TagService tagService;//自动装载Service接口

	/**
	 * 标签云页面
	 * @param model
	 * @return string
	 */
	@RequestMapping(value="/tag-cloud", method = RequestMethod.GET)
	public String tagcloud(Model model) throws Exception
	{
		PageInfo<Tag> list = tagService.listTag(null, null);
		model.addAttribute("list", list.getList());

		return "home/tag";
	}

	/**
	 * 标签搜索文章
	 * @param model
	 * @return string
	 */
	@RequestMapping(value="/tag/{keyword}")
	public String tag(@PathVariable("keyword")String keywords, HttpServletRequest request, Model model) throws Exception
	{
		if(keywords!=null)
		{
			String page  = request.getParameter("page");

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("keywords", keywords);

			PageInfo<Article> pageinfo = articleService.listArticle(param, page);
			model.addAttribute("list",     pageinfo.getList());
			model.addAttribute("keywords", keywords);
			model.addAttribute("page",     pageinfo);
		}

		return "home/tag-atricle";
	}
}
