package main.blog.controller.home;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import main.blog.entity.Article;
import main.blog.service.ArticleService;
import javax.annotation.Resource;

@Controller("home/Index")
public class IndexController extends HomeController{

	@Resource
	private ArticleService articleService;

	/**
	 * 网站首页
	 * @param model
	 * @return string
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(Model model) throws Exception
	{
		List<Article> list = articleService.newArticle(15);
		model.addAttribute("list", list);

		return "home/index";
	}
}
