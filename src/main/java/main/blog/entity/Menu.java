package main.blog.entity;

import lombok.Data;
import main.blog.vo.admin.BaseEntity;

/**
 * 菜单表
 * @author huxg
 */
@Data
public class Menu extends BaseEntity
{
    /** 菜单ID */
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 上级菜单
     */
    private Integer parentId;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单状态
     */
    private Integer status;

    /**
     * 链接类型(1:模块， 2: 菜单 3：按钮)
     */
    private Integer urlType;

    /**
     * 备注说明
     */
    private String description;
}
