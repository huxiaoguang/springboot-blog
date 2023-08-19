package main.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("main.blog.mapper")
public class BlogApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(BlogApplication.class, args);
		System.out.println("博客程序启动成功");
	}
}
