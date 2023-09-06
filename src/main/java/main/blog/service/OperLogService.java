package main.blog.service;

import com.github.pagehelper.PageInfo;
import main.blog.dto.admin.OperLogDTO;
import main.blog.dto.admin.OperLogSearchDTO;
import main.blog.entity.OperLog;

public interface OperLogService
{
    /**
     * 记录操作日志
     * @return
     */
    Boolean insertOperLog(OperLogDTO dto);

    /**
     * 操作日志查询
     * @return
     */
    PageInfo<OperLog> getOperLogPage(OperLogSearchDTO dto);

    /**
     * 操作日志详情
     * @return
     */
    OperLog getOperLogDetail(Integer logId);
}
