package main.blog.controller.home;

import java.util.HashMap;
import java.util.List;
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
import main.blog.service.ArticleService;

@Controller("home/Article")
public class ArticleController extends HomeController
{
	@Resource
	private ArticleService articleService;

	/**
	 * 文章详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/article/{id}.html")
	public String article(@PathVariable("id") Integer id, Model model)  throws Exception
	{
		if(id>0)
		{
			//文章详情
			Article info = articleService.detailArticle(id);
			model.addAttribute("info", info);

			//上一篇下一篇
			List<Article> list = articleService.PreNextArticle(id);
			model.addAttribute("list", list);

			//更新预览数
			articleService.updateViews(id);

			//统计文章评论
			int count = articleService.countComment(id);
			model.addAttribute("count", count);
		}

		return "home/article";
	}

	/**
	 * 分类文章列表
	 * @return string
	 */
	@RequestMapping(value="/category/{cname}")
	public String category(@PathVariable("cname")String cname, HttpServletRequest request, Model model) throws Exception
	{
		if(cname!=null)
		{
			Integer page = Integer.parseInt(request.getParameter("page"));
			ArticleDTO dto = new ArticleDTO();
			dto.setPage(page);
			dto.setCname(cname);

			PageInfo<ArticleVO> pageinfo = articleService.listArticle(dto);
			model.addAttribute("cname", cname);
			model.addAttribute("page",  pageinfo);
			model.addAttribute("list",  pageinfo.getList());
		}

		return "home/category";
	}

	/**
	 * 按月查询文章列表
	 * @return
	 * @return string
	 */
	@RequestMapping(value="/month/{year}/{month}",method=RequestMethod.GET)
	public String months(@PathVariable("year") String year, @PathVariable("month") String month, HttpServletRequest request, Model model) throws Exception
	{
		if(year!=null && month!=null)
		{
			String page  = request.getParameter("page");

			String date = year+'-'+month;
			PageInfo<Article> pageinfo = articleService.getMonthArticle(date, page);

			model.addAttribute("month", month);
			model.addAttribute("page",  pageinfo);
			model.addAttribute("list",  pageinfo.getList());
		}
		return "home/category";
	}
}
