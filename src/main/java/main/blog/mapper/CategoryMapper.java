package main.blog.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.Category;

@Repository
public interface CategoryMapper {

	//文章分类列表
	List<Category> listCategory(Map<String, Object> param);

	//外部调用
	List<Category> getCategoryList();

	//网站导航
	List<Category> getNavList(int limit);

	//分类详情
	Category detailCategory(int id);

	//添加分类
	boolean addCategory(Category category);

	//编辑分类
	boolean editCategory(Category category);

	//删除分类
	boolean deleteCategory(int id);

	//统计分类数目
	int countCategory();

	//更新分类状态
	boolean updateCategoryStatus(Category category);
}
