package main.blog.mapper;

import java.util.HashMap;
import java.util.List;

import main.blog.dto.admin.ArticleSearchDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.vo.admin.ArticleVO;
import org.springframework.stereotype.Repository;

import main.blog.entity.Article;

@Repository
public interface ArticleMapper {

	/**
	 * 最新文章列表
	 * @param limit
	 * @return
	 */
	List<Article> newArticle(int limit);

	/**
	 * 文章列表
	 * @param dto
	 * @return
	 */
	List<ArticleVO> listArticle(ArticleSearchDTO dto);

	/**
	 * 文章详情
	 * @param id
	 * @return
	 */
	Article detailArticle(int id);

	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	Boolean deleteArticle(int id);

	/**
	 * 更新文章预览次数
	 * @param id
	 * @return
	 */
	Integer updateViews(int id);

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
	 * 统计分类文章数
	 * @param id
	 * @return
	 */
	Integer countCategoryArticle(int id);

	/**
	 * 统计子分类数
	 * @param id
	 * @return
	 */
	Integer countSubCategory(int id);

	/**
	 * 更新文章状态
	 * @param dto
	 * @return
	 */
	Boolean updateArticleStatus(StatusDTO dto);

	/**
	 * 统计文章总数
	 * @return
	 */
	Integer countArticle();

	/**
	 * 上一篇文章
	 * @param id
	 * @return
	 */
	Article preArticle(int id);

	/**
	 * 下一篇文章
	 * @param id
	 * @return
	 */
	Article nextArticle(int id);

	/**
	 * 按月统计文章数
	 * @return
	 */
	List<HashMap<String, String>> monthArticle();

	/**
	 * 按月统计查询文章
	 * @param month
	 * @return
	 */
	List<Article> getMonthArticle(String month);
}
