package main.blog.mapper;

import main.blog.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleMenuMapper
{
    /**
     * 新增权限
     * @param roleMenuList
     * @return
     */
    Boolean insertRoleMenu(@Param("roleMenuList") List<RoleMenu> roleMenuList);

    /**
     * 删除角色权限
     * @param roleId
     * @return
     */
    Boolean deleteRoleMenu(Integer roleId);

    /**
     * 角色权限
     * @return
     */
    List<RoleMenu> getRoleMenuList(@Param("roleId") Integer roleId);
}
