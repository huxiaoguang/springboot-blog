package main.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.OperLogDTO;
import main.blog.dto.admin.OperLogSearchDTO;
import main.blog.entity.OperLog;
import main.blog.mapper.OperLogMapper;
import main.blog.service.OperLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class OperLogServiceImpl implements OperLogService
{
    @Resource
    private OperLogMapper operLogMapper;

    @Async
    @Override
    public Boolean insertOperLog(OperLogDTO dto)
    {
        return operLogMapper.insertOperLog(dto);
    }

    @Override
    public PageInfo<OperLog> listOperLog(OperLogSearchDTO dto)
    {
        PageHelper.startPage(dto.getPage(), dto.getLimit());
        List<OperLog> list = operLogMapper.listOperLog(dto);
        return new PageInfo<>(list);
    }

    @Override
    public OperLog getOperLogDetail(Integer logId)
    {
        return operLogMapper.getOperLogDetail(logId);
    }
}
