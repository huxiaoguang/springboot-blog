package main.blog.entity;

import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Comment
{
	private Integer cid;

	private Integer pid;

	private Integer aid;

	private Integer uid;

	private String status;

	private String comment;

	private String title;

	private String username;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
}
