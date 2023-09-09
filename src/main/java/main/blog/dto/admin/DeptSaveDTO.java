package main.blog.dto.admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DeptSaveDTO implements Serializable
{
    @NotNull(message = "部门ID不能为空", groups ={ValidGroupsDTO.Update.class})
    private Integer deptId;

    @NotBlank(message = "部门名称不能为空！")
    private String deptName;

    @NotBlank(message = "上级部门不能为空！")
    private Integer pid;

    private Integer sort = 0;

    @NotNull(message = "部门状态不能为空！")
    private String status;

    private String remark = "";
}
