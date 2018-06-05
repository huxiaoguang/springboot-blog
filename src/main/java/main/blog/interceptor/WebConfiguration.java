package main.blog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	/**
	 * 登录权限拦截器
	 */
	@Autowired
	private LoginInterceptor loginInter;
	
	/**
	 * 前端全局拦截器
	 */
	@Autowired
	private BaseInterceptor baseInter;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) 
    {
    	InterceptorRegistration loginRegistry = registry.addInterceptor(loginInter);
    	InterceptorRegistration baseRegistry  = registry.addInterceptor(baseInter);
    	
        //拦截以/admin/为前缀的 url路径
    	loginRegistry.addPathPatterns("/admin/**");
    	
    	//屏蔽可以通过的url
    	loginRegistry.excludePathPatterns("/admin/login");
    	loginRegistry.excludePathPatterns("/admin/captcha");
    	loginRegistry.excludePathPatterns("/admin/dologin");
    	
    	//屏蔽可以通过的静态资源
    	loginRegistry.excludePathPatterns("/admin/js/**");
    	loginRegistry.excludePathPatterns("/admin/css/**");
    	loginRegistry.excludePathPatterns("/admin/img/**");
    	loginRegistry.excludePathPatterns("/admin/layui/**");
    	
    	//前端全局url资源拦截
    	baseRegistry.addPathPatterns("/");
    	baseRegistry.addPathPatterns("/tag/**");
    	baseRegistry.addPathPatterns("/month/**");
    	baseRegistry.addPathPatterns("/tag-cloud/**");
    	baseRegistry.addPathPatterns("/article/**");
    	baseRegistry.addPathPatterns("/category/**");
    }
}
