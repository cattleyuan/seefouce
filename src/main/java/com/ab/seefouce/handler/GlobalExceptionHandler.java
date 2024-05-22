package com.ab.seefouce.handler;


import com.ab.seefouce.common.SystemJsonResponse;
import com.ab.seefouce.common.enums.GlobalServiceStatusCode;
import com.ab.seefouce.exception.GlobalServiceException;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import static com.ab.seefouce.common.enums.GlobalServiceStatusCode.PARAM_FAILED_VALIDATE;
/**
 * 全局异常处理器，减少 try-catch 语句
 * <p>
 *     定义方式：将需要抛出的异常(第三方异常、自定义异常、系统异常)
 * 传入注解 {@link ExceptionHandler}，再定义方法制作异常的处理方式
 * </p>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalServiceException.class)
    public SystemJsonResponse handleGlobalServiceException(GlobalServiceException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getMessage();
        GlobalServiceStatusCode statusCode = e.getStatusCode();
        log.error("请求地址'{}',状态码:{}，错误信息'{}'", requestURI, statusCode, message);
        return SystemJsonResponse.CUSTOMIZE_MSG_ERROR(statusCode, message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public SystemJsonResponse constraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}', 自定义验证异常'{}'", requestURI, e.getMessage());
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
        return SystemJsonResponse.CUSTOMIZE_MSG_ERROR(PARAM_FAILED_VALIDATE, message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SystemJsonResponse ValidationHandler(MethodArgumentNotValidException e,HttpServletRequest request){
        log.error("数据校验出现问题，异常类型:{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach((item)->{
            String message = item.getDefaultMessage();
            // 获取错误的属性字段名
            String field = item.getField();
            map.put(field,message);
        });

        return SystemJsonResponse.CUSTOMIZE_ERROR(PARAM_FAILED_VALIDATE);
    }
}
