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
    @NotBlank(message = "用户账号不能为空！")
    private String username;

    /** 用户昵称 */
    @NotBlank(message = "用户昵称不能为空！")
    private String nickname;

    /** 用户邮箱 */
    private String email = "";

    /** 手机号码 */
    @NotBlank(message = "手机号码不能为空！")
    private String mobile;

    /** 用户性别 */
    private Integer sex = 1;

    /** 密码 */
    private String password;

    /** 角色Id */
    @NotBlank(message = "请选择角色！")
    private String roleId;

    /** 用户头像 */
    private String avatar = "";

    /** 帐号状态（1正常 2停用） */
    private String status;

    /** 错误次数*/
    private Integer errorNum = 0;
}
