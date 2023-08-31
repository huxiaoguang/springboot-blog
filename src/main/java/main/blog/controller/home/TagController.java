package main.blog.controller.home;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.blog.dto.admin.ArticleSearchDTO;
import main.blog.dto.admin.TagSearchDTO;
import main.blog.vo.admin.ArticleVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;

import main.blog.entity.Tag;
import main.blog.service.ArticleService;
import main.blog.service.TagService;

@Controller("home/tag")
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
	@RequestMapping(value="tag-cloud")
	public String tagcloud(TagSearchDTO dto, Model model)
	{
		PageInfo<Tag> list = tagService.listTag(dto);
		model.addAttribute("list", list.getList());
		return "home/tag";
	}

	/**
	 * 标签搜索文章
	 * @param model
	 * @return string
	 */
	@RequestMapping(value="tag/{keywords}")
	public String tag(@PathVariable("keywords") String keywords, HttpServletRequest request, Model model) throws Exception
	{
		if(keywords!=null)
		{
			ArticleSearchDTO dto = new ArticleSearchDTO();
			dto.setKeywords(keywords);

			PageInfo<ArticleVO> pageinfo = articleService.listArticle(dto);
			model.addAttribute("list", pageinfo.getList());
			model.addAttribute("keywords", keywords);
			model.addAttribute("page", pageinfo);
		}
		return "home/tag-atricle";
	}
}
