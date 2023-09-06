package main.blog.service;

import main.blog.dto.admin.RoleAdminSaveDTO;
import main.blog.entity.RoleAdmin;
import java.util.List;

public interface RoleAdminService
{
    /**
     * 新增角色
     * @param dto
     * @return
     */
    Boolean insertRoleAdmin(RoleAdminSaveDTO dto);

    Boolean deleteRoleAdmin(Integer adminId);

    List<RoleAdmin> getRoleAdminList(Integer adminId);

    List<Integer> getRoleAdminIds(Integer adminId);
}
