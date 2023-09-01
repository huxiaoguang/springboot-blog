package main.blog.mapper;

import main.blog.dto.admin.LoginLogDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLogMapper
{
    Boolean insertLoginLog(LoginLogDTO dto);
}
