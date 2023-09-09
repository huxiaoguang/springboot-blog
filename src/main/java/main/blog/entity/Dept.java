package main.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.blog.vo.admin.BaseEntity;

/**
 * 系统部门表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept extends BaseEntity
{
    private Integer deptId;

    /**
     * 上级ID
     */
    private Integer pid;

    /**
     * 部门编号
     */
    private String deptCode;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 部门状态
     */
    private String status;

    /**
     * 部门/组织名称
     */
    private String deptName;

    /**
     * 描述
     */
    private String description;
}

