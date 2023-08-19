package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import main.blog.entity.Category;
import main.blog.mapper.CategoryMapper;
import main.blog.service.CategoryService;

import javax.annotation.Resource;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService
{
	@Resource
    private CategoryMapper categoryMapper;

	@Override
	public int countCategory()
	{
		return categoryMapper.countCategory();
	}

	@Override
	public List<Category> getCategoryList()
	{
		return categoryMapper.getCategoryList();
	}

	@Override
	public List<Category> listCategory(Map<String, Object> param)
	{
		return categoryMapper.listCategory(param);
	}

	@Override
	public boolean updateCategoryStatus(Category category)
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
	public Category detailCategory(int id)
	{
		Category category = categoryMapper.detailCategory(id);
		return category;
	}

	@Override
	public boolean addCategory(Category category)
	{
		boolean result =  categoryMapper.addCategory(category);
		return  result;
	}

	@Override
	public boolean editCategory(Category category)
	{
		boolean result =  categoryMapper.editCategory(category);
		return  result;
	}

	@Override
	public List<Category> getNavList(int limit)
	{
		List<Category> result = categoryMapper.getNavList(limit);
		return  result;
	}
}
