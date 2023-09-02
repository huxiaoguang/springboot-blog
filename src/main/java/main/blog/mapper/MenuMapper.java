package main.blog.mapper;

import main.blog.dto.admin.MenuSaveDTO;
import main.blog.dto.admin.MenuSearchDTO;
import main.blog.dto.admin.RoleSaveDTO;
import main.blog.dto.admin.RoleSearchDTO;
import main.blog.entity.Menu;
import main.blog.entity.Role;
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
     * 新曾角色
     * @param dto
     * @return
     */
    Boolean insertMenu(MenuSaveDTO dto);

    /**
     * 编辑角色
     * @param dto
     * @return
     */
    Boolean updateMenu(MenuSaveDTO dto);

    /**
     * 删除角色
     * @param menuId
     * @return
     */
    Boolean deleteMenu(Integer menuId);
}
