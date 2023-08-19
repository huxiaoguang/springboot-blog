package main.blog.entity;

import lombok.Data;

@Data
public class User
{
	private Integer id;

	private String username;

	private String nickname;

	private String password;

	private String email;

	private String mobile;

	private int    level;

	private int    score;

	private Long   prevtime;

	private Long   jointime;

	private Long   logintime;

	private String status;
}
