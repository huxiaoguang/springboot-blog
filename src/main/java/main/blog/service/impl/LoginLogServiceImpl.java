package main.blog.service.impl;

import main.blog.dto.admin.LoginLogDTO;
import main.blog.mapper.LoginLogMapper;
import main.blog.service.LoginLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class LoginLogServiceImpl implements LoginLogService
{
    @Resource
    private LoginLogMapper loginLogMapper;

    @Async
    @Override
    public Boolean insertLoginLog(LoginLogDTO dto)
    {
        return loginLogMapper.insertLoginLog(dto);
    }
}
