package main.blog.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import main.blog.entity.AdminBean;

public class LoginInterceptor implements HandlerInterceptor 
{
	//判断url是否是公开 地址（实际使用时将公开 地址配置配置文件中） 
	private List<String> exceptUrls;  
	
    public List<String> getExceptUrls() 
    {  
        return exceptUrls;  
    }  
    
    public void setExceptUrls(List<String> exceptUrls) 
    {  
        this.exceptUrls = exceptUrls;  
    }
    
    //进入 Handler方法之前执行,比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
    {
        //获取请求的url
        String requestUrl = request.getRequestURI().toString();
        
        //放行exceptUrls中配置的url 
	    for (String url:exceptUrls) 
	    { 
	        if(url.endsWith("/**"))
	        {
	            if (requestUrl.startsWith(url.substring(0, url.length() - 3))) 
	            {  
	                return true;
	            }
	        } else if (requestUrl.startsWith(url)) {  
	            return true;
	        }
	    }
	    
        //判断session
        HttpSession  session = request.getSession();
        
        //从session中取出用户身份信息
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		
        if(admin!=null)
        {
        	return true;
        }
        
        //执行这里表示用户身份需要认证，跳转登陆页面
        response.sendRedirect(request.getContextPath()+"/admin/login");
        
        //false表示拦截，不向下执行,true表示放行
        return false;
    }

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}