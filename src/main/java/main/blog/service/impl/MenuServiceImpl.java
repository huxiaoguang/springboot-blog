package main.blog.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.MenuSaveDTO;
import main.blog.dto.admin.MenuSearchDTO;
import main.blog.dto.admin.TreeSearchDTO;
import main.blog.entity.Admin;
import main.blog.entity.Menu;
import main.blog.mapper.MenuMapper;
import main.blog.service.MenuService;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService
{
    @Resource
    private HttpSession session;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public PageInfo<Menu> getMenuList(MenuSearchDTO dto)
    {
        PageHelper.startPage(dto.getPage(), dto.getLimit());
        List<Menu> list = menuMapper.getMenuList(dto);
        return new PageInfo<>(list);
    }

    @Override
    public Boolean insertMenu(MenuSaveDTO dto)
    {
        Admin admin = (Admin) session.getAttribute("admin");

        Menu menu = new Menu();
        BeanCopier.create(MenuSaveDTO.class, Menu.class, false).copy(dto, menu,  null);
        menu.setCreateBy(admin.getUsername());
        menu.setUpdateBy(admin.getUsername());
        return menuMapper.insertMenu(menu);
    }

    @Override
    public Boolean updateMenu(MenuSaveDTO dto)
    {
        Admin admin = (Admin) session.getAttribute("admin");

        Menu menu = new Menu();
        BeanCopier.create(MenuSaveDTO.class, Menu.class, false).copy(dto, menu,  null);
        menu.setUpdateBy(admin.getUsername());
        return menuMapper.updateMenu(menu);
    }

    @Override
    public Boolean deleteMenu(Integer menuId)
    {
        if(this.existMenuChildren(menuId)) {
            throw new RuntimeException("该菜单有下级菜单不能被删除");
        }
        return menuMapper.deleteMenu(menuId);
    }

    @Override
    public Menu getMenuInfo(Integer menuId)
    {
        return menuMapper.getMenuInfo(menuId);
    }

    @Override
    public List<Tree<String>> getTreeMenuList(TreeSearchDTO dto)
    {
        List<Menu> menuList = menuMapper.getMenuList(MenuSearchDTO.builder().status(1).build());
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        for (Menu menu: menuList)
        {
            HashMap map = new HashMap();
            map.put("spread", true);
            map.put("field", dto.getField());
            map.put("title",  menu.getMenuName());
            if(dto.getChecked().contains(menu.getMenuId())) {
                map.put("checked", true);
            }
            nodeList.add(new TreeNode<>(menu.getMenuId().toString(), menu.getParentId().toString(), menu.getMenuName(), menu.getSort()).setExtra(map));
        }
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
        return treeList;
    }

    @Override
    public List<Tree<String>> getTreeRoleMenuList(Integer roleId)
    {
        return null;
    }

    @Override
    public Boolean existMenuUrl(String url)
    {
        return true;
    }

    @Override
    public Boolean existMenuPermission(String permission)
    {
        return true;
    }

    @Override
    public Boolean existMenuChildren(Integer menuId)
    {
        List<Menu> menList = menuMapper.getMenuByParentId(menuId);
        return menList.size() > 0 ? true : false;
    }

    @Override
    public Set<String> getMenuButtonList(String menuUrl)
    {
        return null;
    }
}
