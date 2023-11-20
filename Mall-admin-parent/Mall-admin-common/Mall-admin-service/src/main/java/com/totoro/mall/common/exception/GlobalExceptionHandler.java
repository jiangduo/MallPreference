package com.totoro.mall.common.exception;

import com.totoro.mall.model.vo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Totoro
 * @create 16 20:56
 * @Description: 统一异常处理
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    //全局异常处理，捕获exception
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回json格式数据
    public Result error(Exception e){
        e.printStackTrace();
        return Result.build(null,201,"出现了异常");
    }


    //实际中异常有很多，这里来自定异常
    @ExceptionHandler(GlobalException.class)//里面就是自己定义的异常类
    public Result error(GlobalException e){
        return Result.build(null, e.getResultCodeEnum());
    }


}
