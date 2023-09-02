package main.blog.service;

import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.LoginLogDTO;
import main.blog.dto.admin.LoginLogSearchDTO;
import main.blog.entity.LoginLog;

public interface LoginLogService
{
    /**
     * 新增操作日志
     * @return
     */
    Boolean insertLoginLog(LoginLogDTO dto);

    /**
     * 操作日志查询
     * @return
     */
    PageInfo<LoginLog> listLoginLog(LoginLogSearchDTO dto);
}
