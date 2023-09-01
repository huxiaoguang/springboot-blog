package main.blog.mapper;

import main.blog.dto.admin.OperLogDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface OperLogMapper
{
    Boolean insertOperLog(OperLogDTO dto);
}
