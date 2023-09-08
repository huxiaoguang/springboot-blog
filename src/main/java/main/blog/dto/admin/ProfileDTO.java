package main.blog.dto.admin;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ProfileDTO implements Serializable
{
    /** 用户昵称 */
    @NotEmpty(message = "用户昵称不能为空！")
    private String nickname;

    /** 用户邮箱 */
    @NotEmpty(message = "邮箱不能为空！")
    private String email;

    /** 手机号码 */
    @NotEmpty(message = "手机号码不能为空！")
    private String mobile;

    /** 用户性别 */
    @NotNull(message = "请选择用户性别！")
    private Integer sex = 1;
}
