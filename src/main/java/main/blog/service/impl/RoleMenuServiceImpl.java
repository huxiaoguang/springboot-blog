package main.blog.service.impl;

import main.blog.dto.admin.RoleMenuSaveDTO;
import main.blog.entity.Admin;
import main.blog.entity.RoleMenu;
import main.blog.mapper.RoleMenuMapper;
import main.blog.service.RoleMenuService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleMenuServiceImpl implements RoleMenuService
{
    @Resource
    private HttpSession session;
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Boolean insertRoleMenu(RoleMenuSaveDTO dto)
    {
        Admin admin = (Admin) session.getAttribute("admin");
        List<RoleMenu> roleMenuList = new ArrayList();

        for (Integer menuId : dto.getMenuIds())
        {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(dto.getRoleId());
            roleMenu.setUpdateBy(admin.getUsername());
            roleMenu.setCreateBy(admin.getUsername());
            roleMenuList.add(roleMenu);
        }
        return roleMenuMapper.insertRoleMenu(roleMenuList);
    }
}
