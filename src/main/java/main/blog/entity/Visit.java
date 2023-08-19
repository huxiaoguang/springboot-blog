package main.blog.entity;

import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Visit
{
	private Integer id;

	private String url;

	private String referer;

	private String ip;

	private Long num;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
}
