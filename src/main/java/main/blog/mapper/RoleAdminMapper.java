package main.blog.mapper;

import main.blog.entity.RoleAdmin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleAdminMapper
{
    /**
     * 新增权限
     * @param roleAdminList
     * @return
     */
    Boolean insertRoleAdmin(@Param("roleAdminList") List<RoleAdmin> roleAdminList);

    /**
     * 删除角色权限
     * @param adminId
     * @return
     */
    Boolean deleteRoleAdmin(Integer adminId);

    /**
     * 角色权限
     * @return
     */
    List<RoleAdmin> getRoleAdminList(Integer adminId);
}
