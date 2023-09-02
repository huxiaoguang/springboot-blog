package main.blog.mapper;

import main.blog.dto.admin.RoleSaveDTO;
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
     * @param dto
     * @return
     */
    Boolean insertRole(RoleSaveDTO dto);

    /**
     * 编辑角色
     * @param dto
     * @return
     */
    Boolean updateRole(RoleSaveDTO dto);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    Boolean deleteRole(Integer roleId);
}
