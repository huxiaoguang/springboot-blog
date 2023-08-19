package main.blog.controller.home;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

import main.blog.entity.Comment;
import main.blog.service.CommentService;

@Controller("home/Comment")
public class CommentController extends HomeController
{
	@Resource
	private CommentService commentService;

	/**
	 * 文章评论列表
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/comment/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public Result getListComment(int id, HttpServletRequest request) throws Exception
	{
		HashMap json = new HashMap<>();

		String page  = request.getParameter("page");
		int p = (page == null) || (page == "0") ? 1 : Integer.parseInt(page);

		if(id>0)
		{
			json.put("data",   "");
			json.put("count",  0);
			return Result.success(json, "获取成功");
		}

		PageHelper.startPage(p, 10);
		List<Comment> list = commentService.getListComment(id);

		if(list.size()!=0)
		{
			json.put("data",   list);
			json.put("count",  list.size());
			return Result.success(json, "获取成功");
		}else {
			json.put("data",   "");
			json.put("count",  0);
			return Result.success(json, "获取成功");
		}
	}
}
