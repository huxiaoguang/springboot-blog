package main.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import main.blog.vo.admin.BaseEntity;
import java.util.Date;

@Data
public class Admin extends BaseEntity
{
	private Integer id;

	private String username;

	private String mobile;

	private String nickname;

	private String password;

	private String salt;

	private String email;

	private Integer sex;

	private Integer deptId;

	private String status;

	private String avatar;

	private Integer errorNum;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginTime;
}
