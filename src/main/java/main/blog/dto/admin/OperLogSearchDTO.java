package main.blog.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperLogSearchDTO extends PageDTO
{
	private String title;

	private Integer status;

	private String business;

	private String startDate;

	private String endDate;

	private String createBy;
}
