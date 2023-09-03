package main.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.RoleMenuSaveDTO;
import main.blog.dto.admin.RoleSaveDTO;
import main.blog.dto.admin.RoleSearchDTO;
import main.blog.entity.Admin;
import main.blog.entity.Role;
import main.blog.mapper.RoleMapper;
import main.blog.service.RoleMenuService;
import main.blog.service.RoleService;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService
{
    @Resource
    private HttpSession session;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public PageInfo<Role> getRoleList(RoleSearchDTO dto)
    {
        PageHelper.startPage(dto.getPage(), dto.getLimit());
        List<Role> list = roleMapper.getRoleList(dto);
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertRole(RoleSaveDTO dto)
    {
        if(this.existRoleByName(dto.getRoleName()))
        {
            throw new RuntimeException("角色名称已存在");
        }
        Admin admin = (Admin) session.getAttribute("admin");
        Role role = new Role();
        BeanCopier.create(RoleSaveDTO.class, Role.class, false).copy(dto, role,  null);
        role.setCreateBy(admin.getUsername());
        role.setUpdateBy(admin.getUsername());
        Boolean result1 = roleMapper.insertRole(role);
        Boolean result2 = roleMenuService.insertRoleMenu(RoleMenuSaveDTO.builder().roleId(role.getRoleId()).menuIds(dto.getMenuId()).build());
        return result1 && result2 ? true :false;
    }

    @Override
    public Boolean updateRole(RoleSaveDTO dto)
    {
        Admin admin = (Admin) session.getAttribute("admin");

        Role role = new Role();
        BeanCopier.create(RoleSaveDTO.class, Role.class, false).copy(dto, role,  null);
        role.setUpdateBy(admin.getUsername());
        return roleMapper.updateRole(role);
    }

    @Override
    public Boolean deleteRole(Integer roleId)
    {
        return roleMapper.deleteRole(roleId);
    }

    @Override
    public Role getRoleInfo(Integer roleId)
    {
        Role role = new Role();
        return role;
    }

    @Override
    public List<Role> getRoleList()
    {
        return null;
    }

    @Override
    public List<Role> getRoleListNeSys()
    {
        return null;
    }

    @Override
    public Boolean existRoleByName(String roleName)
    {
        return roleMapper.existRoleByName(roleName);
    }
}
