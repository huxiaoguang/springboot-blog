package main.blog.mapper;

import main.blog.dto.admin.OperLogDTO;
import main.blog.dto.admin.OperLogSearchDTO;
import main.blog.entity.OperLog;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OperLogMapper
{
    Boolean insertOperLog(OperLogDTO dto);

    List<OperLog> listOperLog(OperLogSearchDTO dto);

    OperLog getOperLogDetail(Integer logId);
}
