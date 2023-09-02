package main.blog.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginLogSearchDTO extends PageDTO
{
	private String username;

	private String ip;

	private Integer status;

	private String startDate;

	private String endDate;

	private String createBy;
}
