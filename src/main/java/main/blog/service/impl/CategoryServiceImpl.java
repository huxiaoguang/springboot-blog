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
	public Boolean updateCategoryStatus(Category category)
	{
		return categoryMapper.updateCategoryStatus(category);
	}

	@Override
	public Boolean deleteCategory(int id)
	{
		return categoryMapper.deleteCategory(id);
	}

	@Override
	public Category detailCategory(int id)
	{
		return categoryMapper.detailCategory(id);
	}

	@Override
	public Boolean addCategory(Category category)
	{
		return categoryMapper.addCategory(category);
	}

	@Override
	public Boolean editCategory(Category category)
	{
		return categoryMapper.editCategory(category);
	}

	@Override
	public List<Category> getNavList(int limit)
	{
		List<Category> result = categoryMapper.getNavList(limit);
		return  result;
	}
}
