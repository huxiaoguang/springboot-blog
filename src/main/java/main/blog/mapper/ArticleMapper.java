package main.blog.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.Article;

@Repository
public interface ArticleMapper {

	//最新文章列表
	List<Article> newArticle(int limit);

	//文章列表
	List<Article> listArticle(Map<String, Object> param);

	//文章详情
	Article detailArticle(int id);

	//删除文章
	boolean deleteArticle(int id);

	//更新文章预览次数
	int updateViews(int id);

	//添加文章
	boolean addArticle(Article article);

	//编辑文章
	boolean editArticle(Article article);

	//统计分类文章数
	int countCategoryArticle(int id);

	//统计子分类数
	int countSubCategory(int id);

	//更新文章状态
	boolean updateArticleStatus(Article article);

	//统计文章总数
	int countArticle();

	//上一篇文章
	Article preArticle(int id);

	//下一篇文章
	Article nextArticle(int id);

	//按月统计文章数
	List<HashMap<String, String>> monthArticle();

	//按月统计查询文章
	List<Article> getMonthArticle(String month);
}
