package main.blog.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperLogDTO
{
	private String logId;

	private String title;

	private String action;

	private String method;

	private Integer type;

	private String uri;

	private String ip;

	private String params;

	private String response;

	private Integer status;

	private Integer business;

	private String location;

	private String errorMsg;
}
