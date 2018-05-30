package main.blog.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.blog.entity.CategoryBean;

@Repository
public interface CategoryMapper {
	
	//文章分类列表
	public List<CategoryBean> listCategory(Map<String, Object> param);
	
	//外部调用
	public List<CategoryBean> getCategoryList();
	
	//网站导航
	public List<CategoryBean> getNavList(int limit);
	
	//分类详情
	public CategoryBean detailCategory(int id);
	
	//添加分类
	public boolean addCategory(CategoryBean category);
	
	//编辑分类
	public boolean editCategory(CategoryBean category);
	
	//删除分类
	public boolean deleteCategory(int id);
	
	//统计分类数目
	public int countCategory();
	
	//更新分类状态
	public boolean updateCategoryStatus(CategoryBean category);
}
