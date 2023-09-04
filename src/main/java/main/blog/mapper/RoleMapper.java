package main.blog.mapper;

import main.blog.dto.admin.RoleSearchDTO;
import main.blog.entity.Role;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleMapper
{
    /**
     * 角色管理
     * @param dto
     * @return
     */
    List<Role> getRoleList(RoleSearchDTO dto);

    /**
     * 新曾角色
     * @param role
     * @return
     */
    Boolean insertRole(Role role);

    /**
     * 编辑角色
     * @param role
     * @return
     */
    Boolean updateRole(Role role);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    Boolean deleteRole(Integer roleId);

    /**
     * 角色查询
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
