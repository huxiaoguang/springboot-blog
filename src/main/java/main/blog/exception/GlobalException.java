package main.blog.exception;

import cn.hutool.http.HttpStatus;
import main.blog.utils.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.io.IOException;
import java.util.List;

@RestControllerAdvice
public class GlobalException
{
    /**
     * 全局捕获异常
     * @param exception
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = Exception.class)
    public Result globalException(Exception exception) throws IOException
    {
        if(exception instanceof RuntimeException)
        {
            return Result.failed(HttpStatus.HTTP_BAD_REQUEST, exception.getMessage());
        }else{
            return Result.success();
        }
    }

    /**
     * 捕获错误验证
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public Result validateErrorHandler(BindException exception)
    {
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors())
        {
            List<FieldError> errorList = bindingResult.getFieldErrors();
            return Result.failed(errorList.get(0).getDefaultMessage());
        } else
        {
            return Result.failed("未知错误");
        }
    }
}
