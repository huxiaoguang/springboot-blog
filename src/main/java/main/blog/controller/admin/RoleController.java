package main.blog.controller.admin;

import main.blog.annotation.Log;
import main.blog.dto.admin.RoleSaveDTO;
import main.blog.dto.admin.RoleSearchDTO;
import main.blog.dto.admin.ValidGroupsDTO;
import main.blog.enums.BusinessType;
import main.blog.service.RoleService;
import main.blog.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@RequestMapping("admin/role")
public class RoleController
{
    @Resource
    private RoleService roleService;

    /**
     * 角色管理
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index()
    {
        return "admin/role/index";
    }

    /**
     * 角色列表
     */
    @ResponseBody
    @RequestMapping(value = "data", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result data(RoleSearchDTO dto)
    {
        return Result.success(roleService.getRoleList(dto));
    }

    /**
     * 新增角色
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add()
    {
        return "admin/role/add";
    }

    /**
     * 新增角色
     */
    @ResponseBody
    @Validated(ValidGroupsDTO.Insert.class)
    @Log(title = "新增角色", businessType = BusinessType.INSERT)
    @RequestMapping(value = "insert", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result insert(@Validated RoleSaveDTO dto)
    {
        if(roleService.insertRole(dto))
        {
            return Result.success();
        }else{
            return Result.failed();
        }
    }

    /**
     * 更新角色
     */
    @ResponseBody
    @Validated(ValidGroupsDTO.Update.class)
    @Log(title = "更新角色", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "update", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result update(@Validated RoleSaveDTO dto)
    {
        if(roleService.updateRole(dto))
        {
            return Result.success();
        }else{
            return Result.failed();
        }
    }

    /**
     * 删除角色
     */
    @ResponseBody
    @Log(title = "删除角色", businessType = BusinessType.DELETE)
    @RequestMapping(value = "delete", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result delete(@RequestParam(value="id") Integer roleId)
    {
        if(roleService.deleteRole(roleId))
        {
            return Result.success();
        }else{
            return Result.failed();
        }
    }
}
