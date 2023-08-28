package main.blog.dto.admin;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class ArticleSaveDTO
{
	private Integer id;

	@NotNull(message = "请选择文章分类")
	private Integer cid;

	@NotEmpty(message = "文章标题不能为空")
	private String title;

	@NotEmpty(message = "文章作者不能为空")
	private String author;

	@NotEmpty(message = "文章来源不能为空")
	private String source;

	@NotEmpty(message = "文章内容不能为空")
	private String content;

	private String image;

	@NotEmpty(message = "标签不能为空")
	private String keywords;

	@NotEmpty(message = "文章简介不能为空")
	private String description;

	private String weigh;

	private String status;

	private String cname;

	private String istop;

	private String iscomment;

	@NotNull(message = "创建日期不能为空!")
	@Past(message = "创建日期不能大于现在时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
}
