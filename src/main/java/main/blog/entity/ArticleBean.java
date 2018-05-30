package main.blog.entity;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class ArticleBean 
{
	private int  id;
	
	@Min(value = 1, message = "请选择文章分类!")
	private Integer  category_id;
	
	@NotEmpty(message = "文章标题不能为空")
	private String   title;
	
	@NotEmpty(message = "文章作者不能为空")
	private String   author;
	private String   from;
	
	@NotEmpty(message = "文章内容不能为空")
	private String   content;
	private String   image;
	
	@NotEmpty(message = "标签不能为空")
	private String   keywords;
	
	@NotEmpty(message = "文章简介不能为空")
	private String   description;
	private int      views;
	private int      comments;
	
	@NotNull(message = "创建日期不能为空!")
	@Past(message     = "创建日期不能大于现在时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date     createtime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date     updatetime;
	
	private String   weigh;
	private String   status;
	private String   cname;
	private String   istop;
	private String   iscomment;
	
	public String getIstop() {
		return istop;
	}

	public void setIstop(String istop) {
		this.istop = istop;
	}

	public String getIscomment() {
		return iscomment;
	}
	
	public void setIscomment(String iscomment) {
		this.iscomment = iscomment;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCategory_id() {
		return category_id;
	}
	
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
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
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
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
	
	public int getViews() {
		return views;
	}
	
	public void setViews(int views) {
		this.views = views;
	}
	
	public int getComments() {
		return comments;
	}
	
	public void setComments(int comments) {
		this.comments = comments;
	}
	
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getWeigh() {
		return weigh;
	}
	
	public void setWeigh(String weigh) {
		this.weigh = weigh;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "ArticleBean [id=" + id + ", category_id=" + category_id + ", title=" + title + ", author=" + author
				+ ", from=" + from + ", content=" + content + ", image=" + image + ", keywords=" + keywords
				+ ", description=" + description + ", views=" + views + ", comments=" + comments + ", createtime="
				+ createtime + ", updatetime=" + updatetime + ", weigh=" + weigh + ", status=" + status + ", cname="
				+ cname + ", istop=" + istop + ", iscomment=" + iscomment + "]";
	}
}
