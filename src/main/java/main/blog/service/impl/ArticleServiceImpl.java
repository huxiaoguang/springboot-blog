package main.blog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import main.blog.entity.ArticleBean;
import main.blog.mapper.ArticleMapper;
import main.blog.mapper.CommentMapper;
import main.blog.service.ArticleService;

@Service("articleService") 
public class ArticleServiceImpl implements ArticleService
{	
	@Autowired  
    private ArticleMapper articleMapper;  
	
	@Autowired  
    private CommentMapper commentMapper; 
	
	@Override
	public List<ArticleBean> newArticle(int limit) 
	{
    	List<ArticleBean> list = articleMapper.newArticle(limit);
    	return list;
	}
	
	@Override
	public  ArticleBean detailArticle(int id) 
	{
		ArticleBean info = articleMapper.detailArticle(id);
		return info;
	}
	
	@Override
	public List<ArticleBean> PreNextArticle(int id) 
	{
		List<ArticleBean> list = new ArrayList<>();
		
		ArticleBean preArticle  = articleMapper.preArticle(id);
		ArticleBean nextArticle = articleMapper.nextArticle(id);
		
		list.add(preArticle);
		list.add(nextArticle);
		
		return list;
	}

	@Override
	public int updateViews(int id) 
	{
		return articleMapper.updateViews(id);
	}
	
	@Override
	public int countComment(int id) 
	{
		return commentMapper.countComment(id);
	}
	
	@Override
	public PageInfo<ArticleBean> listArticle(Map<String, Object> param, String page) 
	{	
		int P = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);
		
		PageHelper.startPage(P, 15);
		List<ArticleBean> list = articleMapper.listArticle(param);
		PageInfo<ArticleBean> pageinfo = new PageInfo<ArticleBean>(list);
		
		return pageinfo;
	}

	@Override
	public int countArticle() 
	{
		return articleMapper.countArticle();
	}
	
	@Override
	public boolean addArticle(ArticleBean article) 
	{
		boolean result  = articleMapper.addArticle(article);
		return result;
	}
	
	@Override
	public boolean editArticle(ArticleBean article) 
	{
		boolean result  = articleMapper.editArticle(article);
		return result;
	}

	@Override
	public boolean deleteArticle(int id) 
	{
		boolean result = articleMapper.deleteArticle(id);
		return result;
	}
	
	@Override
	public boolean updateArticleStatus(ArticleBean article) 
	{
		boolean result = articleMapper.updateArticleStatus(article);
		return result;
	}

	@Override
	public int countCategoryArticle(int id) 
	{
		return articleMapper.countCategoryArticle(id);
	}
	
	@Override
	public int countSubCategory(int id) 
	{
		return articleMapper.countSubCategory(id);
	}

	@Override
	public List<HashMap<String, String>> monthArticle() 
	{
		List<HashMap<String, String>> list = articleMapper.monthArticle();
		
		for (HashMap<String, String> vo:list) 
		{
			String[] date = vo.get("months").toString().split("-");
			vo.put("year",  date[0]);
			vo.put("month", date[1]);
		}
		
		return list;
	}
	
	@Override
	public PageInfo<ArticleBean> getMonthArticle(String month, String page) 
	{	
		int P = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);
		
		PageHelper.startPage(P, 15);
		List<ArticleBean> list = articleMapper.getMonthArticle(month);
		PageInfo<ArticleBean> pageinfo = new PageInfo<ArticleBean>(list);
		
		return pageinfo;
	}
}
