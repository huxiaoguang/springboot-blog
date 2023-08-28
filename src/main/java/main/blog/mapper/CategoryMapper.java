package main.blog.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.Category;

@Repository
public interface CategoryMapper {

	/**
	 * 文章分类
	 * @return
	 */
	List<Category> listCategory(Map<String, Object> param);

	/**
	 * 外部调用
	 * @return
	 */
	List<Category> getCategoryList();

	/**
	 * 网站导航
	 * @param limit
	 * @return
	 */
	List<Category> getNavList(int limit);

	/**
	 * 分类详情
	 * @param id
	 * @return
	 */
	Category detailCategory(int id);

	/**
	 * 添加分类
	 * @param category
	 * @return
	 */
	Boolean addCategory(Category category);

	/**
	 * 编辑分类
	 * @param category
	 * @return
	 */
	Boolean editCategory(Category category);

	/**
	 * 删除分类
	 * @param id
	 * @return
	 */
	Boolean deleteCategory(int id);

	/**
	 * 统计分类数目
	 * @return
	 */
	Integer countCategory();

	/**
	 * 更新分类状态
	 * @param category
	 * @return
	 */
	Boolean updateCategoryStatus(Category category);
}
