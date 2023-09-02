package main.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.RoleSaveDTO;
import main.blog.dto.admin.RoleSearchDTO;
import main.blog.entity.Role;
import main.blog.mapper.RoleMapper;
import main.blog.service.RoleService;
import org.springframework.stereotype.Service;
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

    @Override
    public PageInfo<Role> getRoleList(RoleSearchDTO dto)
    {
        PageHelper.startPage(dto.getPage(), dto.getLimit());
        List<Role> list = roleMapper.getRoleList(dto);
        return new PageInfo<>(list);
    }

    @Override
    public Boolean insertRole(RoleSaveDTO dto)
    {
        return roleMapper.insertRole(dto);
    }

    @Override
    public Boolean updateRole(RoleSaveDTO dto)
    {
        return roleMapper.updateRole(dto);
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
    public Boolean existRole(String roleName)
    {
        return true;
    }
}
