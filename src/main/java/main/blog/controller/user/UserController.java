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

import main.blog.entity.User;

@Controller
@SessionAttributes("userinfo")
@RequestMapping(value="/user")
public class UserController {

	@RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(Model model)
	{
		User user = new User();
		model.addAttribute("user", user);
        return "user/login";
    }

	@ResponseBody
	@RequestMapping(value="/dologin",method = RequestMethod.POST, headers="Accept=application/json")
    public JSONObject dologin(@RequestParam("username") String username, @RequestParam("password") String password,
    		Model model)
	{
		JSONObject json = new JSONObject();
		User user = new User();

		if(username.equals("admin") && password.equals("123456"))
		{
			user.setUsername(username);
			user.setPassword(password);
			model.addAttribute("userinfo", user);

			json.put("status",  1);
			json.put("info",    "��¼�ɹ�");
		}else {
			json.put("status",   0);
			json.put("info",     "�û������������");
		}
		return json;
    }

	@ResponseBody
	@RequestMapping("/logout")
	public JSONObject logout(HttpSession session, SessionStatus sessionStatus)
	{
		JSONObject json = new JSONObject();
	    sessionStatus.setComplete();
	    session.removeAttribute("user");

	    if(session.getAttribute("user")==null)
	    {
		    json.put("status",  1);
			json.put("info",    "�˳��ɹ�");
			json.put("url",     "/springmvc/user/regedit");
	    }else {
	    	json.put("status",  0);
			json.put("info",    "�˳�ʧ��");
	    }
		return json;
	}

	@RequestMapping(value="/regedit")
	public String regedit(HttpSession session)
	{
		return "user/regedit";
	}
}
