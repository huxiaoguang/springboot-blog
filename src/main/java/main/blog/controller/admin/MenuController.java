package main.blog.controller.admin;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import main.blog.annotation.Log;
import main.blog.dto.admin.MenuSaveDTO;
import main.blog.dto.admin.MenuSearchDTO;
import main.blog.dto.admin.TreeSearchDTO;
import main.blog.dto.admin.ValidGroupsDTO;
import main.blog.entity.Menu;
import main.blog.enums.BusinessType;
import main.blog.service.MenuService;
import main.blog.service.RoleMenuService;
import main.blog.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin/menu")
public class MenuController
{
    @Resource
    private MenuService menuService;
    @Resource
    private RoleMenuService roleMenuService;
    /**
     * 菜单管理
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index()
    {
        return "admin/menu/index";
    }

    /**
     * 菜单列表
     */
    @ResponseBody
    @RequestMapping(value = "data", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result data(MenuSearchDTO dto)
    {
        return Result.success(menuService.getMenuList(dto));
    }

    /**
     * 新增菜单
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add()
    {
        return "admin/menu/add";
    }

    /**
     * 编辑菜单
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value="id") Integer id, ModelMap map)
    {
        if(ObjectUtil.isNotNull(id))
        {
            Menu menu = menuService.getMenuInfo(id);
            map.put("info", menu);
            if(!menu.getParentId().equals(0))
            {
                Menu parent = menuService.getMenuInfo(menu.getParentId());
                map.put("menuName", parent.getMenuName());
            }else{
                map.put("menuName", "选择上级菜单");
            }
        }
        return "admin/menu/edit";
    }

    /**
     * 新增菜单
     */
    @ResponseBody
    @Validated(ValidGroupsDTO.Insert.class)
    @Log(title = "新增菜单", businessType = BusinessType.INSERT)
    @RequestMapping(value = "insert", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result insert(MenuSaveDTO dto)
    {
        if(menuService.insertMenu(dto))
        {
            return Result.success();
        }else{
            return Result.failed();
        }
    }

    /**
     * 更新菜单
     */
    @ResponseBody
    @Validated(ValidGroupsDTO.Update.class)
    @Log(title = "更新菜单", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "update", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result update(MenuSaveDTO dto)
    {
        if(menuService.updateMenu(dto))
        {
            return Result.success("更新成功");
        }else{
            return Result.failed("更新失败");
        }
    }

    /**
     * 删除菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result delete(@RequestParam(value="id") Integer menuId)
    {
        if(menuService.deleteMenu(menuId))
        {
            return Result.success();
        }else{
            return Result.failed();
        }
    }

    /**
     * 选择图标
     */
    @RequestMapping(value = "icon", method = RequestMethod.GET)
    public String icon(ModelMap map)
    {
        InputStream filePath = this.getClass().getResourceAsStream("/static/admin/font/icon.txt");
        List icons = IoUtil.readLines(filePath, "UTF-8", new ArrayList());
        map.put("icons", icons);
        return "admin/menu/icon";
    }

    @ResponseBody
    @RequestMapping(value = "tree", method = RequestMethod.GET, headers = "Accept=application/json")
    public Result tree(TreeSearchDTO dto)
    {
        return Result.success(menuService.getTreeMenuList(dto));
    }
}
