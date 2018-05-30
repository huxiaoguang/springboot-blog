package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.blog.entity.CategoryBean;
import main.blog.mapper.CategoryMapper;
import main.blog.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService
{	
	@Autowired  
    private CategoryMapper categoryMapper;
	
	@Override
	public int countCategory() 
	{
		return categoryMapper.countCategory();
	}

	@Override
	public List<CategoryBean> getCategoryList() 
	{	
		return categoryMapper.getCategoryList();
	}
	
	@Override
	public List<CategoryBean> listCategory(Map<String, Object> param) 
	{
		return categoryMapper.listCategory(param);
	}

	@Override
	public boolean updateCategoryStatus(CategoryBean category) 
	{
		boolean result = categoryMapper.updateCategoryStatus(category);
		return  result;
	}
	
	@Override
	public boolean deleteCategory(int id) 
	{	
		boolean result =  categoryMapper.deleteCategory(id);
		return  result;
	}
	
	@Override
	public CategoryBean detailCategory(int id) 
	{
		CategoryBean category = categoryMapper.detailCategory(id);
		return category;
	}

	@Override
	public boolean addCategory(CategoryBean category) 
	{
		boolean result =  categoryMapper.addCategory(category);
		return  result;
	}

	@Override
	public boolean editCategory(CategoryBean category) 
	{
		boolean result =  categoryMapper.editCategory(category);
		return  result;
	}

	@Override
	public List<CategoryBean> getNavList(int limit) 
	{	
		List<CategoryBean> result = categoryMapper.getNavList(limit);
		return  result;
	}
}
