package com.syoho.springboottms.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//有参无参构造
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {

    //状态码
    private Integer code;

    //信息
    private String msg;
}