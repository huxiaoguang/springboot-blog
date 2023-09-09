package main.blog.dto.admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class AdminSaveDTO implements Serializable
{
    /** 用户ID */
    @NotNull(message = "用户ID不能为空", groups ={ValidGroupsDTO.Update.class})
    private Integer id;

    /** 用户账号 */
    @NotBlank(message = "用户账号不能为空")
    private String username;

    /** 用户昵称 */
    @NotBlank(message = "用户昵称不能为空")
    private String nickname;

    /** 邮箱 */
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /** 手机号码 */
    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    /** 性别 */
    @NotNull(message = "性别不能为空")
    private Integer sex = 0;

    /** 登录密码 */
    @NotBlank(message = "登录密码不能为空", groups ={ValidGroupsDTO.Insert.class})
    private String password;

    /** 部门ID */
    @NotBlank(message = "请选择所在部门")
    private Integer deptId;

    /** 角色Id */
    @NotBlank(message = "请选择角色")
    private String roleIds;

    /** 用户头像 */
    private String avatar = "";

    /** 帐号状态（1正常 2停用） */
    private String status;

    /** 错误次数*/
    private Integer errorNum = 0;
}
