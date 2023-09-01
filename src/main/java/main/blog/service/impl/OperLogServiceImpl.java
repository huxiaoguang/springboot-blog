package main.blog.service.impl;

import main.blog.dto.admin.OperLogDTO;
import main.blog.entity.OperLog;
import main.blog.mapper.OperLogMapper;
import main.blog.service.OperLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
