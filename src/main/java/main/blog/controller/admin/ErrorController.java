package main.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController
{
	@RequestMapping(value = "404.html", method = RequestMethod.GET)
	public String error()
	{
		return "public/404";
	}
}
