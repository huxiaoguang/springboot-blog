package main.blog.entity;

import lombok.Data;
import main.blog.vo.admin.BaseEntity;

/**
 * 角色权限表
 * @author huxg
 */
@Data
public class RoleAdmin extends BaseEntity
{
    /** 角色ID */
    private Integer id;

    /** 角色ID */
    private Integer roleId;

    /** 菜单ID */
    private Integer adminId;
}
