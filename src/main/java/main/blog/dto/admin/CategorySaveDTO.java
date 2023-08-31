package main.blog.dto.admin;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CategorySaveDTO {

	private Integer cid;

	private Integer pid;

	private Integer isnav;

	private String name;

	private String diyname;

	private String keywords;

	private String description;

	private String status;
}
