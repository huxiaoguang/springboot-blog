package main.blog.entity;

import lombok.Data;

@Data
public class Admin
{
	private Integer id;

	private String username;

	private String password;

	private String salt;

	private String email;

	private String status;

	private String nickname;

	private Long logintime;
}
