package main.blog.aspect;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import main.blog.dto.admin.LoginLogDTO;
import main.blog.enums.BusinessStatus;
import main.blog.service.LoginLogService;
import main.blog.utils.BitwalkerUtil;
import main.blog.utils.IpParseUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Log4j2
@Aspect
@Component
public class LoginLogAspect
{
    @Resource
    private HttpServletRequest request;
    @Resource
    private LoginLogService loginLogService;


    /**
     * 配置切入点
     */
    @Pointcut("execution(public * main.blog.controller.admin.LoginController.dologin(..))")
    public void loginLog() {
    }

    /**
     * 前置方法,在目标方法执行前执行
     *
     * @param joinPoint
     */
    @Before("loginLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    /**
     * 环绕通知
     * @param joinPoint
     */
    @Around("loginLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object result = joinPoint.proceed();
        Map<String, String> paramMap = ServletUtil.getParamMap(request);

        LoginLogDTO loginLog = new LoginLogDTO();

        // 密码托敏
        paramMap.put("password", DesensitizedUtil.password(paramMap.get("password")));

        // 获取请求参数
        loginLog.setParams(JSONUtil.toJsonStr(paramMap));

        // 获取用户名
        loginLog.setUsername(paramMap.get("username"));
        loginLog.setCreateBy(paramMap.get("username"));
        loginLog.setUpdateBy(paramMap.get("username"));

        // 获取相应参数
        loginLog.setResponse(JSONUtil.toJsonStr(result));

        // 获取状态码
        JSONObject jsonObject = new JSONObject(result);
        String code = jsonObject.getStr("code");
        loginLog.setStatus(code.equals("200") ? BusinessStatus.SUCCESS.getCode() : BusinessStatus.FAIL.getCode());

        //loginLog.setIp(BitwalkerUtil.getClientIp(request));
        loginLog.setIp(ServletUtil.getClientIP(request));
        loginLog.setBrowser(BitwalkerUtil.getBrowser(request).toString());
        loginLog.setOs(BitwalkerUtil.getOsName(request));
        loginLog.setLocation(IpParseUtil.parse("127.0.0.1"));

        loginLogService.insertLoginLog(loginLog);
        return result;
    }

    /**
     * 后置方法，与@Before相反，在目标方法执行完毕后执行
     * @param joinPoint
     */
    @After("loginLog()")
    public void after(JoinPoint joinPoint) {
    }

    /**
     * 后置通知，在将返回值返回时执行
     * @param result
     */
    @AfterReturning(value = "loginLog()", returning = "result")
    public void doAfterReturning(Object result) throws Throwable {
    }

    /**
     * 后置异常通知
     * @param joinPoint
     * @param exception
     * @throws Throwable
     */
    @AfterThrowing(value = "loginLog()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) throws Throwable {
    }
}
