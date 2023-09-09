package main.blog.controller.admin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import main.blog.annotation.Log;
import main.blog.dto.admin.*;
import main.blog.entity.Admin;
import main.blog.enums.BusinessType;
import main.blog.service.AdminService;
import main.blog.service.RoleAdminService;
import main.blog.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Controller
@RequestMapping(value = "system/admin")
public class AdminController
{
    @Resource
    private HttpSession session;
    @Resource
    private AdminService adminService;
    @Resource
    private RoleAdminService roleAdminService;
    @Value("${larblog.upload.user-avatar-file-path}")
    private String userAvatarFilePath;

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
    @RequestMapping(value = "data", method = RequestMethod.GET, headers = "Accept=application/json")
    public Result data(AdminSearchDTO dto)
    {
        return Result.success(adminService.getAdminPage(dto));
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
     * 修改密码
     */
    @RequestMapping(value = "editPass", method = RequestMethod.GET)
    public String pass(@RequestParam Map params, ModelMap search)
    {
        search.put("params", params);
        return "admin/admin/editPass";
    }

    /**
     * 修改密码
     */
    @ResponseBody
    @Log(title = "修改密码", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "editPass", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result editPass(@Validated EditPassDTO dto)
    {
        if(adminService.updatePassWord(dto))
        {
            return Result.success("修改密码成功");
        }else{
            return Result.failed("修改密码成功");
        }
    }

    /**
     * 更新账号状态
     * @return
     */
    @ResponseBody
    @Log(title = "更新账号状态", businessType = BusinessType.UPDATE)
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

    /**
     * 个人资料
     */
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String profile(HttpSession session, ModelMap map)
    {
        // 用户信息
        Admin admin = (Admin) session.getAttribute("admin");
        map.put("user", adminService.getAdminInfo(admin.getId()));
        return "admin/admin/profile";
    }

    /**
     * 更新用户资料
     */
    @Log(title = "基本资料", businessType = BusinessType.UPDATE)
    @ResponseBody
    @RequestMapping(value = "profile", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result update(@Validated ProfileDTO dto)
    {
        if(adminService.updateProfile(dto))
        {
            return Result.success();
        }else{
            return Result.failed();
        }
    }

    @ResponseBody
    @Log(title = "头像修改", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "avatar", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result updateUserAvatar(@RequestParam("file") MultipartFile file)
    {
        // 判断文件是否为空
        if(file.isEmpty()) {
            return Result.failed("上传文件为空");
        }
        // 判断是否是图片格式
        if(!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
            return Result.failed("上传的文件不是jpg或png格式");
        }
        // 开始上传文件的操作
        if(adminService.updateAvatar(this.userAvatarFilePath, file))
        {
            return Result.success("文件上传成功");
        }else {
            return Result.failed("文件上传失败");
        }
    }

    @RequestMapping(value = "avatar", method = RequestMethod.GET, headers = "Accept=application/json")
    public void getUserAvatar(HttpServletResponse response) throws IOException
    {
        Admin admin = (Admin) session.getAttribute("admin");
        Admin result = adminService.getAdminInfo(admin.getId());

        ServletOutputStream outputStream = null;
        InputStream inputStream = null;

        try {
            String imgPath = result.getAvatar();
            if(StrUtil.isEmpty(imgPath))
            {
                ClassPathResource classPathResource = new ClassPathResource("/static/admin/img/head.jpg");
                inputStream = classPathResource.getInputStream();
            }else{
                inputStream = FileUtil.getInputStream(imgPath);
            }
            response.setContentType("image/jpg");
            outputStream = response.getOutputStream();

            int len = 0;
            byte[] buffer = new byte[4096];
            while ((len = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally {
            outputStream.close();
            inputStream.close();
        }
    }
}
