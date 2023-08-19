package main.blog.service;

import java.util.List;
import java.util.Map;
import main.blog.entity.Category;

public interface CategoryService {

	/**
	 * 文章分类列表
	 * @param param
	 * @return
	 */
	List<Category> listCategory(Map<String, Object> param);

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
	 * 网站导航
	 * @param limit
	 * @return
	 */
	List<Category> getNavList(int limit);

	/**
	 * 统计分类数目
	 * @return
	 */
	int countCategory();

	/**
	 * 获取文章分类
	 * @return
	 */
	List<Category> getCategoryList();

	/**
	 * 分类详情
	 * @param id
	 * @return
	 */
	Category detailCategory(int id);

	/**
	 * 删除分类
	 * @param id
	 * @return
	 */
	Boolean deleteCategory(int id);

	/**
	 * 更新状态
	 * @param category
	 * @return
	 */
	Boolean updateCategoryStatus(Category category);
}
