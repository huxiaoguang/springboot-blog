package main.blog.mapper;

import main.blog.dto.admin.MenuSearchDTO;
import main.blog.entity.Menu;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MenuMapper
{
    /**
     * 菜单管理
     * @param dto
     * @return
     */
    List<Menu> getMenuList(MenuSearchDTO dto);

    Menu getMenuInfo(Integer menuId);

    /**
     * 判断是否有下级菜单
     * @param menuId
     * @return
     */
    List<Menu> getMenuByParentId(Integer menuId);

    /**
     * 新曾角色
     * @param dto
     * @return
     */
    Boolean insertMenu(Menu dto);

    /**
     * 编辑角色
     * @param dto
     * @return
     */
    Boolean updateMenu(Menu dto);

    /**
     * 删除角色
     * @param menuId
     * @return
     */
    Boolean deleteMenu(Integer menuId);
}
