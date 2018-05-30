package main.blog.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.alibaba.fastjson.JSONObject;

import main.blog.entity.UserBean;

@Controller
@SessionAttributes("userinfo")
@RequestMapping(value="/user")
public class UserController {
	
	/**
	 * 用户登录试图
	 * @return String
	 */
	@RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(Model model) 
	{
		UserBean user = new UserBean();
		model.addAttribute("user", user);
        return "user/login";//用户登录试图
    }
	
	/**
	 * 用户登录操作
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="/dologin",method = RequestMethod.POST, headers="Accept=application/json")
    public JSONObject dologin(@RequestParam("username") String username, @RequestParam("password") String password,
    		Model model) 
	{
		JSONObject json = new JSONObject();
		UserBean   user = new UserBean();
		
		if(username.equals("admin") && password.equals("123456"))
		{
			user.setUsername(username);
			user.setPassword(password);
			model.addAttribute("userinfo", user);
			
			json.put("status",  1);
			json.put("info",    "登录成功");
		}else {
			json.put("status",   0);
			json.put("info",     "用户名或密码错误");
		}
		return json;
    } 
	
	/**
	 * 用户退出操作(清楚session，并跳到指定的页面)
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/logout")
	public JSONObject logout(HttpSession session, SessionStatus sessionStatus)
	{
		JSONObject json = new JSONObject();
	    sessionStatus.setComplete();
	    session.removeAttribute("user");//无法清除页面上的session
	    
	    if(session.getAttribute("user")==null)
	    {
		    json.put("status",  1);
			json.put("info",    "退出成功");
			json.put("url",     "/springmvc/user/regedit");
	    }else {
	    	json.put("status",  0);
			json.put("info",    "退出失败");
	    }
		return json;
	}
	
	/**
	 * 用户注册试图
	 * @return String
	 */
	@RequestMapping(value="/regedit")
	public String regedit(HttpSession session)
	{
		return "user/regedit";
	} 
} 
