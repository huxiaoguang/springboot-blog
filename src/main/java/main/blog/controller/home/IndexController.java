package main.blog.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.blog.entity.ArticleBean;
import main.blog.service.ArticleService;

@Controller("home/Index")
public class IndexController extends HomeController{
	
	@Autowired
	private ArticleService articleService;//自动装载Service接口
	
	/**
	 * 网站首页
	 * @param model 
	 * @return string
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index(Model model) throws Exception
	{
		List<ArticleBean> list = articleService.newArticle(15);
		model.addAttribute("list", list);
		
		return "home/index";
	}
}
