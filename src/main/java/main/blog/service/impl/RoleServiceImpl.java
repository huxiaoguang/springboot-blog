package main.blog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.RoleMenuSaveDTO;
import main.blog.dto.admin.RoleSaveDTO;
import main.blog.dto.admin.RoleSearchDTO;
import main.blog.entity.Admin;
import main.blog.entity.Role;
import main.blog.entity.RoleAdmin;
import main.blog.mapper.RoleMapper;
import main.blog.service.RoleMenuService;
import main.blog.service.RoleService;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    public PageInfo<Role> getRolePage(RoleSearchDTO dto)
    {
        PageHelper.startPage(dto.getPage(), dto.getLimit());
        List<Role> list = roleMapper.getRolePage(dto);
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertRole(RoleSaveDTO dto)
    {
        if(this.existRoleByName(dto.getRoleName())) {
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
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateRole(RoleSaveDTO dto)
    {
        Admin admin = (Admin) session.getAttribute("admin");

        Role role = this.getRoleInfo(dto.getRoleId());
        if(ObjectUtil.isNull(role)) {
            throw new RuntimeException("角色不存在");
        }
        if(!role.getRoleName().equals(dto.getRoleName())) {
            if(this.existRoleByName(dto.getRoleName())) {
                throw new RuntimeException("角色名称已存在");
            }
        }

        BeanCopier.create(RoleSaveDTO.class, Role.class, false).copy(dto, role,  null);
        role.setUpdateBy(admin.getUsername());
        Boolean result1 = roleMapper.updateRole(role);
        Boolean result2 = roleMenuService.deleteRoleMenu(dto.getRoleId());
        Boolean result3 = roleMenuService.insertRoleMenu(RoleMenuSaveDTO.builder().roleId(role.getRoleId()).menuIds(dto.getMenuId()).build());
        return result1 && result2 && result3 ? true :false;
    }

    @Override
    public Boolean deleteRole(Integer roleId)
    {
        Boolean result1 = roleMenuService.deleteRoleMenu(roleId);
        Boolean result2 = roleMapper.deleteRole(roleId);
        return result1 && result2 ? true :false;
    }

    @Override
    public Role getRoleInfo(Integer roleId)
    {
        return roleMapper.getRoleInfo(roleId);
    }

    @Override
    public List<Role> getRoleList()
    {
        return roleMapper.getRoleList();
    }

    @Override
    public Boolean existRoleByName(String roleName)
    {
        return roleMapper.existRoleByName(roleName);
    }
}
