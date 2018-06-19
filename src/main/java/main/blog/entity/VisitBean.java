package main.blog.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class VisitBean 
{
	private int id;
	private String url;
	private String referer;
	private String ip;
	private long num;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createtime;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getReferer() {
		return referer;
	}
	
	public void setReferer(String referer) {
		this.referer = referer;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public long getNum() {
		return num;
	}
	
	public void setNum(long num) {
		this.num = num;
	}
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
