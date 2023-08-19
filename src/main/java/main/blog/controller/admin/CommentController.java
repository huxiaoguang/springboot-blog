package main.blog.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import main.blog.utils.Result;
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

	@Resource
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
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "/comment/delete", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result delete(@RequestParam(defaultValue = "0") Integer id)
	{
		if (id == 0) {
			return Result.failed("参数错误");
		}

		Boolean result = commentService.deleteComment(id);
		if (result) {
			return Result.success("删除成功");
		} else {
			return Result.failed("删除失败");
		}
	}

	/**
	 * 更新评论状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/comment/updateStatus", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result updateStatus(@RequestParam(defaultValue = "0") Integer id, String status)
	{
		Comment comment = new Comment();
		comment.setCid(id);
		comment.setStatus(status);

		Boolean result = commentService.updateCommentStatus(comment);
		if (result)
		{
			return Result.success("操作成功");
		} else {
			return Result.failed("操作失败");
		}
	}
}
