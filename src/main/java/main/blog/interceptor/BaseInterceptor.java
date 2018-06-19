package main.blog.interceptor;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import main.blog.entity.CategoryBean;
import main.blog.entity.LinkBean;
import main.blog.entity.TagBean;
import main.blog.service.ArticleService;
import main.blog.service.CategoryService;
import main.blog.service.CommentService;
import main.blog.service.LinkService;
import main.blog.service.TagService;
import main.blog.service.VisitService;

@Component
public class BaseInterceptor implements HandlerInterceptor 
{
	@Resource
	private CategoryService categoryService;
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private LinkService linkService;
	
	@Resource
	private TagService tagService;
	
	@Resource
	private VisitService visitService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 
		//网站导航
		List<CategoryBean> nav = categoryService.getNavList(15);
		request.setAttribute("nav", nav);
		
		//友情链接
		List<LinkBean> link = linkService.getListLink(15);
		request.setAttribute("link", link);
		
		//网站标签
		List<TagBean> tag = tagService.getListTag(15);
		request.setAttribute("tag", tag);
		
		//统计文章数
		int countArticle = articleService.countArticle();
		request.setAttribute("countArticle", countArticle);
		
		//统计评论数
		int numComment 	= commentService.numComment();
		request.setAttribute("numComment", numComment);
		
		//文章归档
		List<HashMap<String, String>> monthArticle = articleService.monthArticle();
		request.setAttribute("monthArticle", monthArticle);
		
		//当前访问量
		int visitCount = visitService.getVisitCount();
		request.setAttribute("visitCount", visitCount);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}