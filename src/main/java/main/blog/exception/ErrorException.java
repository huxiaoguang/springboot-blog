package main.blog.exception;

import cn.hutool.http.HttpStatus;
import main.blog.utils.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ErrorException implements ErrorController
{
    @GetMapping(value = {"/error"})
    public Result error()
    {
        return Result.failed(HttpStatus.HTTP_NOT_FOUND, "请检查请求路径或者类型是否正确");
    }

    @RequestMapping(value = "/error", produces = "text/html")
    public void errorPageHandler(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.sendRedirect(request.getContextPath()+"/404.html");
    }
}
