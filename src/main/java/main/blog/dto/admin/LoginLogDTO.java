package main.blog.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录日志记录表
 * @author huxg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginLogDTO
{
    /**日志ID*/
    private String logId;

    /** 操作系统 */
    private String os;

    /** 登录IP地址 */
    private String ip;

    /** 用户账号 */
    private String username;

    /** 登录状态 1成功 2失败 */
    private Integer status;

    /** 登录地点 */
    private String location = "";

    /** 浏览器类型 */
    private String browser = "";

    /**请求参数*/
    private String params;

    /**请求返回的结果*/
    private String response;

    /**操作人*/
    private String createBy;

    /**更新人*/
    private String updateBy;
}