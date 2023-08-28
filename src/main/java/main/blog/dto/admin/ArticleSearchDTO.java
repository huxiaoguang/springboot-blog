package main.blog.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSearchDTO extends PageDTO
{
	private Integer cid;

	private String cname;

	private String keywords;
}
