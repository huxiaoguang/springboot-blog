package main.blog.entity;

import lombok.Data;
import main.blog.vo.admin.BaseEntity;

/**
 * 角色
 * @author huxg
 */
@Data
public class Role extends BaseEntity
{
    /** 角色ID */
    private Integer roleId;

    /** 系统角色 */
    private Integer userType = 0;

    /** 角色名称 */
    private String roleName;

    /** 角色排序 */
    private Integer roleSort;

    /** 角色状态（1正常 2停用） */
    private Integer status;

    /** 删除标志（0代表存在 1代表删除） */
    private Integer isDelete;

    /** 备注说明 */
    private String remarks;
}
