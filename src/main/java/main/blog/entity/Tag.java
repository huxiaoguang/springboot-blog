package main.blog.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class Tag
{
	private Integer id;

	private String  tagname;

	private String  status;

	private String  description;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;
}
