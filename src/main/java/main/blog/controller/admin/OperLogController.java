package main.blog.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import main.blog.dto.admin.OperLogSearchDTO;
import main.blog.service.OperLogService;
import main.blog.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@RequestMapping("admin/operlog")
public class OperLogController
{
    @Resource
    private OperLogService operLogService;

    /**
     * 操作记录
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index()
    {
        return "admin/operlog/index";
    }

    @ResponseBody
    @RequestMapping(value = "data", method = RequestMethod.POST, headers = "Accept=application/json")
    public Result data(OperLogSearchDTO dto)
    {
        return Result.success(operLogService.getOperLogPage(dto));
    }

    /**
     * 日志详情
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String edit(@RequestParam(value="id") Integer logId, ModelMap map)
    {
        if(ObjectUtil.isNotNull(logId))
        {
            map.addAttribute("info", operLogService.getOperLogDetail(logId));
        }
        return "admin/operlog/detail";
    }
}
