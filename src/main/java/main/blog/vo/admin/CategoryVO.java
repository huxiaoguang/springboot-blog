package main.blog.vo.admin;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CategoryVO extends BaseEntity
{
	private Integer cid;

	private Integer pid;

	private Integer isnav;

	private String name;

	private String diyname;

	private String status;

	private String keywords;

	private String description;
}
