package main.blog.service.impl;

import main.blog.dto.admin.RoleAdminSaveDTO;
import main.blog.entity.Admin;
import main.blog.entity.RoleAdmin;
import main.blog.mapper.RoleAdminMapper;
import main.blog.service.RoleAdminService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleAdminServiceImpl implements RoleAdminService
{
    @Resource
    private HttpSession session;
    @Resource
    private RoleAdminMapper roleAdminMapper;

    @Override
    public Boolean insertRoleAdmin(RoleAdminSaveDTO dto)
    {
        Admin admin = (Admin) session.getAttribute("admin");
        List<RoleAdmin> roleAdminList = new ArrayList();

        for (Integer roleId : dto.getRoleIds())
        {
            RoleAdmin roleAdmin = new RoleAdmin();
            roleAdmin.setRoleId(roleId);
            roleAdmin.setAdminId(dto.getAdminId());
            roleAdmin.setUpdateBy(admin.getUsername());
            roleAdmin.setCreateBy(admin.getUsername());
            roleAdminList.add(roleAdmin);
        }
        return roleAdminMapper.insertRoleAdmin(roleAdminList);
    }

    @Override
    public Boolean deleteRoleAdmin(Integer adminId)
    {
        return roleAdminMapper.deleteRoleAdmin(adminId);
    }

    @Override
    public List<RoleAdmin> getRoleAdminList(Integer adminId)
    {
        return roleAdminMapper.getRoleAdminList(adminId);
    }

    @Override
    public List<Integer> getRoleAdminIds(Integer adminId)
    {
        List<Integer> roleIdArr = new ArrayList();
        List<RoleAdmin> roleAdminList = this.getRoleAdminList(adminId);

        Map<Integer, List<Integer>> roleIds = roleAdminList.stream().collect(Collectors.groupingBy(RoleAdmin::getAdminId, Collectors.mapping(RoleAdmin::getRoleId, Collectors.toList())));
        if(roleIds.size()>0)
        {
            roleIdArr = roleIds.get(adminId);
        }
        return roleIdArr;
    }
}
