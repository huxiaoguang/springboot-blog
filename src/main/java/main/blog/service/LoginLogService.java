package main.blog.service;

import main.blog.dto.admin.LoginLogDTO;

public interface LoginLogService
{
    Boolean insertLoginLog(LoginLogDTO dto);
}
