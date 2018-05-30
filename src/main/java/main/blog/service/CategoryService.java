package main.blog.service;

import java.util.List;
import java.util.Map;

import main.blog.entity.CategoryBean;

public interface CategoryService {
	
	//文章分类列表
	public List<CategoryBean> listCategory(Map<String, Object> param);
	
	//添加分类
	public boolean addCategory(CategoryBean category);
	
	//编辑分类
	public boolean editCategory(CategoryBean category);
	
	//网站导航
	public List<CategoryBean> getNavList(int limit);
		
	//统计分类数目
	public int countCategory();
	
	//获取文章分类
	public List<CategoryBean> getCategoryList();
	
	//分类详情
	public CategoryBean detailCategory(int id);
	
	//删除分类
	public boolean deleteCategory(int id);
	
	//更新状态
	public boolean updateCategoryStatus(CategoryBean category);
}
