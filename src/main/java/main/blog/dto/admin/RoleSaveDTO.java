package main.blog.dto.admin;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class RoleSaveDTO implements Serializable
{
    @NotNull(message = "角色ID不能为空", groups ={ValidGroupsDTO.Update.class})
    private Integer roleId;

    @NotBlank(message = "角色名称不能为空！")
    private String roleName;

    @NotNull(message = "角色排序不能为空！")
    private Integer sort;

    @NotNull(message = "角色状态不能为空！")
    private Integer status = 1;

    /** 删除标志（0代表存在 1代表删除） */
    private Integer isDelete = 0;

    /** 备注说明 */
    private String description = "";

    @Valid
    @NotEmpty(message = "请选择角色权限")
    private List<Integer> menuId;
}
