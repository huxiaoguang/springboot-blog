package main.blog.entity;

public class AdminLogBean 
{
	private int id;
	private	int admin_id;
	private String username;
	private String url;
	private String title;
	private String content;
	private String ip;
	private String useragent;
	private long createtime;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getAdmin_id() {
		return admin_id;
	}
	
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getUseragent() {
		return useragent;
	}
	
	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}
	
	public long getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}  
}
