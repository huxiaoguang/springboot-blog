package main.blog.service.impl;

import cn.hutool.core.lang.tree.Tree;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.MenuSaveDTO;
import main.blog.dto.admin.MenuSearchDTO;
import main.blog.entity.Menu;
import main.blog.mapper.MenuMapper;
import main.blog.service.MenuService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
        return true;
    }

    @Override
    public Boolean updateMenu(MenuSaveDTO dto)
    {
        return true;
    }

    @Override
    public Boolean deleteMenu(Integer menuId)
    {
        return true;
    }

    @Override
    public Menu getMenuInfo(Integer menuId)
    {
        return menuMapper.getMenuInfo(menuId);
    }

    @Override
    public List<Tree<String>> getTreeMenuList()
    {
        return null;
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
        return true;
    }

    @Override
    public Set<String> getMenuButtonList(String menuUrl)
    {
        return null;
    }
}
