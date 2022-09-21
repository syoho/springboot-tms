package com.syoho.springboottms.exception;

import com.syoho.springboottms.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//统一异常处理类
//aop 面向切面编程
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null).message("An error occurred. Please try to change the Name of teacher.");
    }


    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.fail(null).message("An error occurred. Please try to change the Name of teacher.");
    }

    //自定义异常处理
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result error(CustomException e){
        e.printStackTrace();
        return Result.fail(null).message("An error occurred. Please try to change the Name of teacher.");
    }


}
