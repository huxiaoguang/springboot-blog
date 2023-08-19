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

	@Min(value = 1, message = "请选择文章分类!")
	private Integer  category_id;

	@NotEmpty(message = "文章标题不能为空")
	private String   title;

	@NotEmpty(message = "文章作者不能为空")
	private String   author;
	private String   from;

	@NotEmpty(message = "文章内容不能为空")
	private String   content;
	private String   image;

	@NotEmpty(message = "标签不能为空")
	private String   keywords;

	@NotEmpty(message = "文章简介不能为空")
	private String   description;
	private int      views;
	private int      comments;

	private String   weigh;
	private String   status;
	private String   cname;
	private String   istop;
	private String   iscomment;

	@NotNull(message = "创建日期不能为空!")
	@Past(message     = "创建日期不能大于现在时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
}
