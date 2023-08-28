package main.blog.entity;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Article
{
	private Integer id;

	private Integer cid;

	private String title;

	private String author;

	private String source;

	private String content;

	private String image;

	private String keywords;

	private String description;

	private Integer views;

	private Integer comments;

	private String weigh;

	private String status;

	private String cname;

	private String isTop;

	private String isComment;

	private Date createTime;

	private Date updateTime;
}
