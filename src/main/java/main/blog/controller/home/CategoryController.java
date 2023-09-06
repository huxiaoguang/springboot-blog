package main.blog.controller.home;

import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.ArticleSearchDTO;
import main.blog.entity.Article;
import main.blog.service.ArticleService;
import main.blog.vo.admin.ArticleVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("home/category")
public class CategoryController extends HomeController
{
	@Resource
	private ArticleService articleService;

	/**
	 * 分类文章列表
	 * @return string
	 */
	@RequestMapping(value="/category/{cname}")
	public String category(@PathVariable("cname") String cname, HttpServletRequest request, Model model) throws Exception
	{
		if(cname!=null)
		{
			ArticleSearchDTO dto = new ArticleSearchDTO();
			dto.setCname(cname);

			PageInfo<ArticleVO> pageinfo = articleService.getArticlePage(dto);
			model.addAttribute("cname", cname);
			model.addAttribute("page",  pageinfo);
			model.addAttribute("list",  pageinfo.getList());
		}

		return "home/category";
	}
}
