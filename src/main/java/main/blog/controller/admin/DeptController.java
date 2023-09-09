package main.blog.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import main.blog.annotation.Log;
import main.blog.dto.admin.*;
import main.blog.entity.Dept;
import main.blog.entity.Menu;
import main.blog.enums.BusinessType;
import main.blog.service.DeptService;
import main.blog.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@Controller("Dept")
@RequestMapping("admin/dept")
public class DeptController
{
    @Resource
    private DeptService deptService;

    /**
     * 部门管理
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index()
    {
        return "admin/dept/index";
    }

    /**
     * 全部部门
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "data", method = RequestMethod.GET, headers = "Accept=application/json")
    public Result data(DeptSearchDTO searchReqDTO)
    {
        return Result.success(deptService.getDeptPage(searchReqDTO));
    }

    /**
     * 新增部门
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add()
    {
        return "admin/dept/add";
    }

    /**
     * 新增部门
     */
    @ResponseBody
    @Validated(ValidGroupsDTO.Insert.class)
    @Log(title = "新增部门", businessType = BusinessType.INSERT)
    @RequestMapping(value = "insert", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result insert(DeptSaveDTO dto)
    {
        if(deptService.insertDept(dto))
        {
            return Result.success();
        }else{
            return Result.failed();
        }
    }

    /**
     * 编辑部门
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value="id") Integer id, ModelMap map)
    {
        if(ObjectUtil.isNotNull(id))
        {
            Dept dept = deptService.getDeptInfo(id);
            map.put("info", dept);
            map.put("menuName", "请选择部门");
        }
        return "admin/dept/edit";
    }

    /**
     * 更新部门
     */
    @ResponseBody
    @Validated(ValidGroupsDTO.Update.class)
    @Log(title = "更新菜单", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "update", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result update(DeptSaveDTO dto)
    {
        if(deptService.updateDept(dto))
        {
            return Result.success("更新成功");
        }else{
            return Result.failed("更新失败");
        }
    }

    /**
     * 更新部门状态
     * @return
     */
    @ResponseBody
    @Log(title = "更新部门状态", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result updateStatus(StatusDTO dto)
    {
        if (deptService.updateDeptStatus(dto))
        {
            return Result.success("操作成功");
        } else {
            return Result.failed("操作失败");
        }
    }

    /**
     * 删除部门
     */
    @ResponseBody
    @Log(title = "删除部门", businessType = BusinessType.DELETE)
    @RequestMapping(value = "delete", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result delete(@RequestParam(value="id") Integer deptId)
    {
        if(deptService.deleteDept(deptId))
        {
            return Result.success();
        }else{
            return Result.failed();
        }
    }

    /**
     * 树形部门
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "tree", method = RequestMethod.GET, headers = "Accept=application/json")
    public Result tree(TreeSearchDTO dto)
    {
        return Result.success(deptService.getTreeDeptList(dto));
    }
}

