package main.blog.entity;

import lombok.Data;

@Data
public class AdminLog
{
	private Integer id;

	private	int admin_id;

	private String username;

	private String url;

	private String title;

	private String content;

	private String ip;

	private String useragent;

	private long createtime;
}
