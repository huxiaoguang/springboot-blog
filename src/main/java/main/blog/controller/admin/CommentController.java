package main.blog.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import main.blog.entity.Comment;
import main.blog.service.CommentService;

@Controller("Comment")
@RequestMapping(value = "/admin")
public class CommentController {

	@Autowired
	private CommentService commentService;

	/**
	 * 评论列表
	 * @return string
	 */
	@RequestMapping(value = "/comment/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model)
	{
		String keywords = request.getParameter("keywords");
		String page     = request.getParameter("page");

		Map<String, Object> param = new HashMap<String, Object>();

		// 关键词查询
		if (keywords != null && keywords != "") {
			param.put("title", keywords.trim());
			model.addAttribute("keywords", keywords);
		}

		PageInfo<Comment> pageinfo = commentService.listComment(param, page);

		model.addAttribute("page", pageinfo);
		model.addAttribute("list", pageinfo.getList());

		return "admin/comment/comment";
	}

	/**
	 * 删除评论操作
	 * @param model
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "/comment/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject delete(@RequestParam(defaultValue = "0") Integer id)
	{
		JSONObject json = new JSONObject();

		if (id == 0) {
			json.put("status", 0);
			json.put("msg", "参数错误");
			return json;
		}

		boolean result = commentService.deleteComment(id);

		if (result) {
			json.put("status", 1);
			json.put("msg", "删除成功");
		} else {
			json.put("status", 0);
			json.put("msg", "删除失败");
		}
		return json;
	}

	/**
	 * 更新评论状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/comment/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONObject updateStatus(@RequestParam(defaultValue = "0") Integer id, String status)
	{
		JSONObject json = new JSONObject();

		Comment comment = new Comment();
		comment.setCid(id);
		comment.setStatus(status);

		boolean result = commentService.updateCommentStatus(comment);

		if (result)
		{
			json.put("status", 1);
			json.put("msg", "操作成功");
		} else {
			json.put("status", 0);
			json.put("msg", "操作失败");
		}
		return json;
	}
}
