package main.blog.service.impl;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageSerializable;
import main.blog.dto.admin.CategorySearchDTO;
import main.blog.service.ArticleService;
import main.blog.utils.Result;
import main.blog.vo.admin.ArticleVO;
import main.blog.vo.admin.CategoryVO;
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
	@Resource
	private ArticleService articleService;

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
	public PageInfo<CategoryVO> listCategory(CategorySearchDTO dto)
	{
		PageHelper.startPage(dto.getPage(), dto.getLimit());
		List<CategoryVO> list = categoryMapper.listCategory(dto);
		return new PageInfo<>(list);
	}

	@Override
	public Boolean updateCategoryStatus(Category category)
	{
		return categoryMapper.updateCategoryStatus(category);
	}

	@Override
	public Boolean deleteCategory(Integer id)
	{
		Integer countSubCate = articleService.countSubCategory(id);
		if (countSubCate > 0)
		{
			throw new RuntimeException("该分类下有子分类，不能被删除");
		}
		Integer countCateArticle = articleService.countCategoryArticle(id);
		if (countCateArticle > 0)
		{
			throw new RuntimeException("该分类下有文章，不能被删除");
		}
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
