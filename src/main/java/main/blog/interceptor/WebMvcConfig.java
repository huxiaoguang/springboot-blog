package main.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig {
	
	@Configuration
	public class MyWebMvcConfigurer implements WebMvcConfigurer {
	 
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) 
	    {
	        //拦截以/admin/为前缀的 url路径
	        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**");
	    }
	}
}
