package main.blog.dto.admin;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginDTO
{
	@NotEmpty(message = "账号不能为空")
	private String username;

	@NotEmpty(message = "密码不能为空")
	private String password;

	@NotEmpty(message = "验证码")
	private String captcha;
}
