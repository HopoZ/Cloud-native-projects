package org.software.productclient.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.software.productclient.common.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice(basePackages="com.example.controller")
public class GlobalExceptionHandler {

    private static final Log log = LogFactory.get();


    //统一异常处理@ExceptionHandler,主要用于Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回json串
    public Result<?> error(HttpServletRequest request, Exception e){
        log.error("异常信息：",e);
        return Result.error("-1", "系统异常");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody//返回json串
    public Result<?> notValidExceptionError(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        StringBuilder errorMsg = new StringBuilder() ;

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            errorMsg.append(fieldErrors.get(fieldErrors.size() - 1).getDefaultMessage()).append("!");
        }

        return Result.error("-1", errorMsg.toString());
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody//返回json串
    public Result<?> customError(HttpServletRequest request, CustomException e){
        return Result.error(e.getCode(), e.getMsg());
    }
}
