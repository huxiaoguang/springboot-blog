package main.blog.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.ArticleBean;

@Repository
public interface ArticleMapper {
	
	//最新文章列表
	public List<ArticleBean> newArticle(int limit);
	
	//文章列表
	public List<ArticleBean> listArticle(Map<String, Object> param);
	
	//文章详情
	public ArticleBean detailArticle(int id);
	
	//删除文章
	public boolean deleteArticle(int id);
	
	//更新文章预览次数
	public int updateViews(int id);
	
	//添加文章
	public boolean addArticle(ArticleBean article);
	
	//编辑文章
	public boolean editArticle(ArticleBean article);
	
	//统计分类文章数
	public int countCategoryArticle(int id);
	
	//统计子分类数
	public int countSubCategory(int id);
	
	//更新文章状态
	public  boolean updateArticleStatus(ArticleBean article);
	
	//统计文章总数
	public int countArticle();
	
	//上一篇文章
	public ArticleBean preArticle(int id);
	
	//下一篇文章
	public ArticleBean nextArticle(int id);
	
	//按月统计文章数
	public List<HashMap<String, String>>  monthArticle();
	
	//按月统计查询文章
	public List<ArticleBean> getMonthArticle(String month);
}
