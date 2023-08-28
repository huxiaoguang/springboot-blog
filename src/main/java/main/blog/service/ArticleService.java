package main.blog.service;

import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;

import main.blog.dto.admin.ArticleSaveDTO;
import main.blog.dto.admin.ArticleSearchDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.entity.Article;
import main.blog.vo.admin.ArticleVO;

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
	Integer countCategoryArticle(Integer id);

	/**
	 * 统计子分类数
	 * @param id
	 * @return
	 */
	Integer countSubCategory(int id);

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
	 * @return
	 */
	PageInfo<ArticleVO> listArticle(ArticleSearchDTO dto);

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
	Boolean addArticle(ArticleSaveDTO article);

	/**
	 * 编辑文章
	 * @param article
	 * @return
	 */
	Boolean editArticle(ArticleSaveDTO article);

	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	Boolean deleteArticle(int id);

	/**
	 * 更新状态
	 * @param dto
	 * @return
	 */
	Boolean updateArticleStatus(StatusDTO dto);

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
