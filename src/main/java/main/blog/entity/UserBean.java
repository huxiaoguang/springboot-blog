package main.blog.entity;

public class UserBean 
{
	private int id;
	private String username;
	private String nickname;
	private String password;
	private String email;
	private String mobile;
	private int    level;
	private int    score;
	private long   prevtime;
	private long   jointime;
	private long   logintime;
	
	private String status;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public long getJointime() {
		return jointime;
	}
	
	public void setJointime(long jointime) {
		this.jointime = jointime;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public long getPrevtime() {
		return prevtime;
	}

	public void setPrevtime(long prevtime) {
		this.prevtime = prevtime;
	}

	public long getLogintime() {
		return logintime;
	}
	
	public void setLogintime(long logintime) {
		this.logintime = logintime;
	}
}
