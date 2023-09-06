package main.blog.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import main.blog.annotation.Log;
import main.blog.dto.admin.AdminSaveDTO;
import main.blog.dto.admin.AdminSearchDTO;
import main.blog.dto.admin.StatusDTO;
import main.blog.dto.admin.ValidGroupsDTO;
import main.blog.enums.BusinessType;
import main.blog.service.AdminService;
import main.blog.service.RoleAdminService;
import main.blog.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@RequestMapping(value = "system/admin")
public class AdminController
{
    @Resource
    private AdminService adminService;
    @Resource
    private RoleAdminService roleAdminService;

    /**
     * 账号管理
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index()
    {
        return "admin/admin/index";
    }

    /**
     * 账号列表
     */
    @ResponseBody
    @RequestMapping(value = "data", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result data(AdminSearchDTO dto)
    {
        return Result.success(adminService.getAdminList(dto));
    }

    /**
     * 新增账号
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add()
    {
        return "admin/admin/add";
    }

    /**
     * 新增账号
     */
    @Log(title = "新增账号", businessType = BusinessType.INSERT)
    @ResponseBody
    @Validated(ValidGroupsDTO.Insert.class)
    @RequestMapping(value = "insert", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result insert(AdminSaveDTO dto)
    {
        if(adminService.insertAdmin(dto))
        {
            return Result.success("新增成功");
        }else{
            return Result.failed("新增失败");
        }
    }

    /**
     * 编辑用户
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value="id") Integer adminId, ModelMap map)
    {
        if(ObjectUtil.isNotNull(adminId))
        {
            map.addAttribute("roleIds", roleAdminService.getRoleAdminIds(adminId));
            map.put("info", adminService.getAdminInfo(adminId));
        }
        return "admin/admin/edit";
    }

    /**
     * 编辑账号
     */
    @Log(title = "编辑账号", businessType = BusinessType.UPDATE)
    @ResponseBody
    @Validated(ValidGroupsDTO.Insert.class)
    @RequestMapping(value = "update", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result update(AdminSaveDTO dto)
    {
        if(adminService.updateAdmin(dto))
        {
            return Result.success("编辑成功");
        }else{
            return Result.failed("编辑失败");
        }
    }

    /**
     * 删除用户
     */
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result delete(@RequestParam(value="id") Integer adminId)
    {
        if(adminService.deleteAdmin(adminId))
        {
            return Result.success("删除成功");
        }else{
            return Result.failed("删除失败");
        }
    }

    /**
     * 更新标签状态
     * @return
     */
    @ResponseBody
    @Log(title = "更新标签状态", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result updateStatus(StatusDTO dto)
    {
        if (adminService.updateAdminStatus(dto))
        {
            return Result.success("操作成功");
        } else {
            return Result.failed("操作失败");
        }
    }
}