package main.blog.service;

import main.blog.dto.admin.OperLogDTO;

public interface OperLogService
{
    /**
     * 记录操作日志
     * @return
     */
    Boolean insertOperLog(OperLogDTO dto);
}
