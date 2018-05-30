package main.blog.controller.home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

import main.blog.entity.CommentBean;
import main.blog.service.CommentService;

@Controller("home/Comment")
public class CommentController extends HomeController
{
	@Autowired
	private CommentService commentService;//自动装载Service接口
	
	/**
	 * 文章评论列表
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/comment/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public JSONObject getListComment(int id, HttpServletRequest request) throws Exception
	{
		JSONObject json = new JSONObject();
		
		String page  = request.getParameter("page");
		int p = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);
		
		if(id>0)
		{
			PageHelper.startPage(p, 10);
			List<CommentBean> list = commentService.getListComment(id);
			
			if(list.size()!=0)
			{
				json.put("data",   list);
				json.put("count",  list.size());
			}else {
				json.put("data",   "");
				json.put("count",  0);
			}
		}
		return json;
	}
}
