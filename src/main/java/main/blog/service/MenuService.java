package main.blog.service;

import cn.hutool.core.lang.tree.Tree;
import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.MenuSaveDTO;
import main.blog.dto.admin.MenuSearchDTO;
import main.blog.dto.admin.TreeSearchDTO;
import main.blog.entity.Menu;
import java.util.List;
import java.util.Set;

public interface MenuService
{
    /**
     * 获取菜单
     * @return
     */
    PageInfo<Menu> getMenuPage(MenuSearchDTO dto);

    /**
     * 新增菜单
     * @param dto
     * @return
     */
    Boolean insertMenu(MenuSaveDTO dto);

    /**
     * 更新菜单
     * @param dto
     * @return
     */
    Boolean updateMenu(MenuSaveDTO dto);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    Boolean deleteMenu(Integer menuId);

    /**
     * 获取菜单信息
     * @param menuId
     * @return
     */
    Menu getMenuInfo(Integer menuId);

    /**
     * 获全部菜单列表
     * @return
     */
    List<Tree<String>> getTreeMenuList(TreeSearchDTO dto);

    /**
     * 获取角色权限列表
     * @param roleId
     * @return
     */
    List<Tree<String>> getTreeRoleMenuList(Integer roleId);

    /**
     * 判断URL是否存在
     * @param url
     * @return
     */
    Boolean existMenuUrl(String url);

    /**
     * 判断权限节点是否存在
     * @param permission
     * @return
     */
    Boolean existMenuPermission(String permission);

    /**
     * 判断是否有下级菜单
     * @param menuId
     * @return
     */
    Boolean existMenuChildren(Integer menuId);

    /**
     * 根据路径获取按钮
     * @param menuUrl
     * @return
     */
    Set<String> getMenuButtonList(String menuUrl);
}
