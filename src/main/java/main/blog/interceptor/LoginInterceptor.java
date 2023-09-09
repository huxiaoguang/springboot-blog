package main.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import main.blog.entity.Admin;

@Component
public class LoginInterceptor implements HandlerInterceptor
{
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        //判断session
        HttpSession  session = request.getSession();

        //从session中取出用户身份信息
		Admin admin = (Admin) session.getAttribute("admin");

        if(admin!=null)
        {
        	return true;
        }

        //执行这里表示用户身份需要认证，跳转登陆页面
        response.sendRedirect(request.getContextPath()+"/admin/login");

        //false表示拦截，不向下执行,true表示放行
        return false;
    }
}
