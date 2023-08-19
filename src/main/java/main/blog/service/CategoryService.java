package main.blog.service;

import java.util.List;
import java.util.Map;
import main.blog.entity.Category;

public interface CategoryService {

	// 文章分类列表
	List<Category> listCategory(Map<String, Object> param);

	// 添加分类
	boolean addCategory(Category category);

	//编辑分类
	boolean editCategory(Category category);

	//网站导航
	List<Category> getNavList(int limit);

	//统计分类数目
	int countCategory();

	//获取文章分类
	List<Category> getCategoryList();

	//分类详情
	Category detailCategory(int id);

	//删除分类
	boolean deleteCategory(int id);

	//更新状态
	boolean updateCategoryStatus(Category category);
}
