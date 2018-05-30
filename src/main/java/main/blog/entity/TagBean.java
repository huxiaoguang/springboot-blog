package main.blog.entity;

public class TagBean 
{
	private int     id;
	private String  tagname;
	private String  status; 
	private String  description;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTagname() {
		return tagname;
	}
	
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
