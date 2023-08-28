package main.blog.controller.home;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.blog.dto.admin.ArticleDTO;
import main.blog.vo.admin.ArticleVO;
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
public class TagController extends HomeController
{
	@Resource
	private ArticleService articleService;
	@Resource
	private TagService tagService;

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
	@RequestMapping(value="/tag/{keywords}")
	public String tag(@PathVariable("keywords") String keywords, HttpServletRequest request, Model model) throws Exception
	{
		if(keywords!=null)
		{
			Integer page = Integer.parseInt(request.getParameter("page"));
			ArticleDTO dto = new ArticleDTO();
			dto.setPage(page);
			dto.setKeywords(keywords);

			PageInfo<ArticleVO> pageinfo = articleService.listArticle(dto);
			model.addAttribute("list", pageinfo.getList());
			model.addAttribute("keywords", keywords);
			model.addAttribute("page", pageinfo);
		}
		return "home/tag-atricle";
	}
}
