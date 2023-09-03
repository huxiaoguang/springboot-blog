package main.blog.service;

import main.blog.dto.admin.RoleMenuSaveDTO;

public interface RoleMenuService
{
    /**
     * 新增角色
     * @param dto
     * @return
     */
    Boolean insertRoleMenu(RoleMenuSaveDTO dto);
}
