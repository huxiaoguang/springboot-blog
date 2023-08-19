package main.blog.entity;

import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Category {

	private Integer id;

	private Integer pid;

	private Integer isnav;

	private String name;

	private String diyname;

	private String keywords;

	private String description;

	private String status;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
}
