package main.blog.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CategoryBean {

	private int 	id;
	private int    	pid;
	private int 	isnav;
	private String  name;
	private String  diyname;
	private String  keywords;
	private String  description;
	private String  status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date    updatetime;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPid() {
		return pid;
	}
	
	public int getIsnav() {
		return isnav;
	}

	public void setIsnav(int isnav) {
		this.isnav = isnav;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getKeywords() {
		return keywords;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDiyname() {
		return diyname;
	}

	public void setDiyname(String diyname) {
		this.diyname = diyname;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "CategoryBean [id=" + id + ", pid=" + pid + ", name=" + name + ", diyname=" + diyname + ", keywords="
				+ keywords + ", description=" + description + ", status=" + status + ", updatetime=" + updatetime + "]";
	}
}
