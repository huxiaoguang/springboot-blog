package main.blog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import main.blog.entity.Article;

public interface ArticleService {

	/**
	 * 最新文章
	 * @param limit
	 * @return
	 */
	List<Article> newArticle(int limit);

	/**
	 * 获取文章信息
	 * @param id
	 * @return
	 */
	Article detailArticle(int id);

	/**
	 * 统计分类文章数
	 * @param id
	 * @return
	 */
	int countCategoryArticle(int id);

	/**
	 * 统计子分类数
	 * @param id
	 * @return
	 */
	int countSubCategory(int id);

	/**
	 * 上一篇、下一篇
	 * @param id
	 * @return
	 */
	List<Article> PreNextArticle(int id);

	/**
	 * 更新预览次数
	 * @param id
	 * @return
	 */
	int updateViews(int id);

	/**
	 * 统计文章评论
	 * @param id
	 * @return
	 */
	int countComment(int id);

	/**
	 * 文章列表
	 * @param param
	 * @param page
	 * @return
	 */
	PageInfo<Article> listArticle(Map<String, Object> param, String page);

	/**
	 * 统计文章总数
	 * @return
	 */
	int countArticle();

	/**
	 * 添加文章
	 * @param article
	 * @return
	 */
	Boolean addArticle(Article article);

	/**
	 * 编辑文章
	 * @param article
	 * @return
	 */
	Boolean editArticle(Article article);

	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	Boolean deleteArticle(int id);

	/**
	 * 更新状态
	 * @param article
	 * @return
	 */
	Boolean updateArticleStatus(Article article);

	/**
	 * 按月统计
	 * @return
	 */
	List<HashMap<String, String>>  monthArticle();

	/**
	 * 按月统计查询文章
	 * @param month
	 * @param page
	 * @return
	 */
	PageInfo<Article> getMonthArticle(String month, String page);
}
