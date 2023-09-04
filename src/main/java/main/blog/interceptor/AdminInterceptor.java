package main.blog.interceptor;

import main.blog.entity.Admin;
import main.blog.entity.Category;
import main.blog.entity.Link;
import main.blog.entity.Tag;
import main.blog.service.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Component
public class AdminInterceptor implements HandlerInterceptor
{
	@Resource
	private HttpSession session;

	@Resource
	private MenuService menuService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception
	{
		Admin admin = (Admin) session.getAttribute("admin");

		// 系统菜单
		if(admin!=null)
		{
			request.setAttribute("menuList", menuService.getTreeRoleMenuList(1));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}
}
