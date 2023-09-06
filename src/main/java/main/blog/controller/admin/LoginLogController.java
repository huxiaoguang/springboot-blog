package main.blog.controller.admin;

import main.blog.dto.admin.LoginLogSearchDTO;
import main.blog.service.LoginLogService;
import main.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("admin/loginlog")
public class LoginLogController
{
    @Autowired
    private LoginLogService loginLogService;

    /**
     * 登录日志
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index()
    {
        return "admin/loginlog/index";
    }

    /**
     * 登录日志
     */
    @ResponseBody
    @RequestMapping(value = "data", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result data(LoginLogSearchDTO dto)
    {
        return Result.success(loginLogService.getLoginLogPage(dto));
    }
}
