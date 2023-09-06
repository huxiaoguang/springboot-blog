package main.blog.mapper;

import main.blog.dto.admin.LoginLogDTO;
import main.blog.dto.admin.LoginLogSearchDTO;
import main.blog.entity.LoginLog;
import main.blog.entity.OperLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginLogMapper
{
    Boolean insertLoginLog(LoginLogDTO dto);

    List<LoginLog> getLoginLogPage(LoginLogSearchDTO dto);
}
