package main.blog.aspect;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import main.blog.annotation.Log;
import main.blog.dto.admin.OperLogDTO;
import main.blog.enums.BusinessStatus;
import main.blog.service.OperLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * 操作日志记录处理
 *
 * @author huxg
 */
@Aspect
@Component
public class LogAspect
{
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private HttpServletRequest request;

    @Resource
    private OperLogService operLogService;

    @Pointcut("@annotation(main.blog.annotation.Log)")
    public void logPointCut() {}

    /**
     * 处理完请求后执行
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult)
    {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception exception, Object jsonResult)
    {
        try
        {
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            OperLogDTO operLog = new OperLogDTO();

            operLog.setStatus(BusinessStatus.SUCCESS.getCode());
            operLog.setIp(ServletUtil.getClientIP(request));
            operLog.setResponse(JSONUtil.toJsonStr(jsonResult));
            operLog.setUri(request.getRequestURI());

            if (exception != null) {
                operLog.setStatus(BusinessStatus.FAIL.getCode());
                operLog.setErrorMsg(exception.getMessage());
            }

            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setAction(className + "." + methodName + "()");
            operLog.setMethod(request.getMethod());
            getControllerMethodDescription(joinPoint, controllerLog, operLog);

            operLogService.insertOperLog(operLog);
        } catch (Exception e)
        {
            log.error("操作记录异常:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息
     * @param log 日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, OperLogDTO operLog)
    {
        operLog.setTitle(log.title());
        operLog.setBusiness(log.businessType().ordinal());

        if (log.isSaveRequestData()) {
            setRequestValue(joinPoint, operLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, OperLogDTO operLog)
    {
        String requestMethod = operLog.getMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod))
        {
            String params = argsArrayToString(joinPoint.getArgs());
            operLog.setParams(params);
        }
        else
        {
            Map<?, ?> paramsMap = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operLog.setParams(paramsMap.toString());
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint)
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (int i = 0; i < paramsArray.length; i++)
            {
                Object paramObj = paramsArray[i];
                if (ObjectUtil.isNotNull(paramObj) && !isFilterObject(paramObj))
                {
                    String paramStr = JSONUtil.toJsonStr(paramObj);
                    params += paramStr + " ";
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     * @param obj 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object obj)
    {
        Class<?> clazz = obj.getClass();
        if (clazz.isArray())
        {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz))
        {
            Collection collection = (Collection) obj;
            for (Iterator iter = collection.iterator(); iter.hasNext();)
            {
                return iter.next() instanceof MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz))
        {
            Map map = (Map) obj;
            for (Iterator iter = map.entrySet().iterator(); iter.hasNext();)
            {
                Map.Entry entry = (Map.Entry) iter.next();
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return obj instanceof MultipartFile || obj instanceof HttpServletRequest || obj instanceof HttpServletResponse || obj instanceof BindingResult;
    }
}
