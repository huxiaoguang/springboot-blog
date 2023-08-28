package main.blog.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO extends BaseEntity
{
	private Integer id;

	private Integer categoryId;

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

	private String istop;

	private String iscomment;
}
