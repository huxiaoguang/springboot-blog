package main.blog.service;

import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.RoleSaveDTO;
import main.blog.dto.admin.RoleSearchDTO;
import main.blog.entity.Role;
import java.util.List;

public interface RoleService
{
    /**
     * 获取角色
     */
    PageInfo<Role> getRolePage(RoleSearchDTO dto);

    /**
     * 获取所有角色列表
     * @return
     */
    List<Role> getRoleList();

    /**
     * 新增角色
     * @param dto
     * @return
     */
    Boolean insertRole(RoleSaveDTO dto);

    /**
     * 更新角色
     * @param dto
     * @return
     */
    Boolean updateRole(RoleSaveDTO dto);

    /**
     *  删除角色
     * @param roleId
     * @return
     */
    Boolean deleteRole(Integer roleId);

    /**
     * 获取角色信息
     * @param roleId
     * @return
     */
    Role getRoleInfo(Integer roleId);

    /**
     * 判断角色是否存在
     * @param roleName
     * @return
     */
    Boolean existRoleByName(String roleName);
}
