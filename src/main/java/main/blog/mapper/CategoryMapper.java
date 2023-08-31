package main.blog.mapper;

import java.util.List;
import java.util.Map;

import main.blog.dto.admin.CategorySaveDTO;
import main.blog.dto.admin.CategorySearchDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.vo.admin.CategoryVO;
import org.springframework.stereotype.Repository;

import main.blog.entity.Category;

@Repository
public interface CategoryMapper {

	/**
	 * 文章分类
	 * @return
	 */
	List<CategoryVO> listCategory(CategorySearchDTO dto);

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
	Category detailCategory(Integer id);

	/**
	 * 添加分类
	 * @param dto
	 * @return
	 */
	Boolean addCategory(CategorySaveDTO dto);

	/**
	 * 编辑分类
	 * @param dto
	 * @return
	 */
	Boolean editCategory(CategorySaveDTO dto);

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
	 * @return
	 */
	Boolean updateCategoryStatus(StatusDTO dto);
}
