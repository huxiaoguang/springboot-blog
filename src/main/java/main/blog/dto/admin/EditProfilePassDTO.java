package main.blog.dto.admin;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EditProfilePassDTO
{
	@NotEmpty(message = "原密码不能为空")
	private String password;

	@NotEmpty(message = "新密码不能为空")
	private String newpass;

	@NotEmpty(message = "重输不能为空")
	private String renewpass;
}
