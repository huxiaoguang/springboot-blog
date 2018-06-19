package main.blog.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class WebUtil {

	//获取客户端ip
	public static String getClientIp(HttpServletRequest request) 
	{ 
		String ip=request.getHeader("x-forwarded-for");
	
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){  
			ip=request.getHeader("Proxy-Client-IP");  
		}
	
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){  
			ip=request.getHeader("WL-Proxy-Client-IP");  
		}
	
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){  
			ip=request.getHeader("X-Real-IP");  
		}
	
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){  
		    ip=request.getRemoteAddr();  
		}
		
		return ip;
	}
	
	//获取来源
	public static String getReferer(HttpServletRequest request)
	{
		String referer = request.getHeader("referer");
		return referer;
	}
	
	//获取访问地址
	public static String getUrl(HttpServletRequest request)
	{
		String url = request.getRequestURL().toString();
		return url;
	}
}