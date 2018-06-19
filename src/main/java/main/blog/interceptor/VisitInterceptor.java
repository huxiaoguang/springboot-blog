package main.blog.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import main.blog.entity.VisitBean;
import main.blog.service.VisitService;
import main.blog.utils.WebUtil;

@Component
public class VisitInterceptor implements HandlerInterceptor 
{
	@Resource
	private VisitService visitService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String ip      = WebUtil.getClientIp(request);
		String url 	   = WebUtil.getUrl(request);
		String referer = WebUtil.getReferer(request);
		
		VisitBean visit = new VisitBean();
		visit.setIp(ip);
		visit.setReferer(referer);
		visit.setUrl(url);
		visitService.addVisit(visit);
		
		return true;
	}
}