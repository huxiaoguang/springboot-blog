package main.blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.Article;

public interface ArticleService {

	//最新文章
	public List<Article> newArticle(int limit);

	//获取文章信息
	public Article detailArticle(int id);

	//统计分类文章数
	public int countCategoryArticle(int id);

	//统计子分类数
	public int countSubCategory(int id);

	//上一篇、下一篇
	public List<Article> PreNextArticle(int id);

	//更新预览次数
	public int updateViews(int id);

	//统计文章评论
	public int countComment(int id);

	//文章列表
	public PageInfo<Article> listArticle(Map<String, Object> param, String page);

	//统计文章总数
	public int countArticle();

	//添加文章
	public boolean addArticle(Article article);

	//编辑文章
	public boolean editArticle(Article article);

	//删除文章
	public boolean deleteArticle(int id);

	//更新状态
	public boolean updateArticleStatus(Article article);

	//按月统计
	public List<HashMap<String, String>>  monthArticle();

	//按月统计查询文章
	public PageInfo<Article> getMonthArticle(String month, String page);
}
