package com.totoro.mall.common.exception;

import com.totoro.mall.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author Totoro
 * @create 16 22:50
 * @Description: 定义异常
 */
@Data//生成get set方法
public class GlobalException extends RuntimeException{
    private Integer code;
    private String message;

    private ResultCodeEnum resultCodeEnum; // 封装错误状态码和错误消息

    public GlobalException(ResultCodeEnum resultCodeEnum){
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.resultCodeEnum = resultCodeEnum;
    }

    public GlobalException(Integer code, String message){
        this.code = code;
        this.message = message;
    }

}
