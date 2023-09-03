package main.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.blog.vo.admin.BaseEntity;

/**
 * 操作日志记录表
 * @author huxg
 */
@Data
public class OperLog extends BaseEntity
{
    private String logId;

    private String title;

    private String action;

    private String method;

    private Integer type;

    private String uri;

    private String ip;

    private String params;

    private String response;

    private Integer status;

    private Integer business;

    private String location;

    private String errorMsg;
}
