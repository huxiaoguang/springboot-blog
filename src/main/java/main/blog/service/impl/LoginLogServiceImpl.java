package main.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.LoginLogDTO;
import main.blog.dto.admin.LoginLogSearchDTO;
import main.blog.entity.LoginLog;
import main.blog.entity.OperLog;
import main.blog.mapper.LoginLogMapper;
import main.blog.service.LoginLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public PageInfo<LoginLog> getLoginLogPage(LoginLogSearchDTO dto)
    {
        PageHelper.startPage(dto.getPage(), dto.getLimit());
        List<LoginLog> list = loginLogMapper.getLoginLogPage(dto);
        return new PageInfo<>(list);
    }
}
