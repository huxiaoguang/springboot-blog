package main.blog.service;

import main.blog.dto.admin.RoleMenuSaveDTO;
import main.blog.entity.RoleMenu;
import java.util.List;

public interface RoleMenuService
{
    /**
     * 新增角色
     * @param dto
     * @return
     */
    Boolean insertRoleMenu(RoleMenuSaveDTO dto);

    Boolean deleteRoleMenu(Integer roleId);

    List<RoleMenu> getRoleMenuList(Integer roleId);

    List<Integer> getRoleMenuIds(Integer roleId);
}
