package com.syoho.springboottms.result;

import lombok.Data;

//统一返回结果
@Data
public class Result<T> {

    private Integer code; //状态码

    private String message; //返回状态

    private T data; //范型类

    public Result() {}

/*    //返回成功,无data
    public static<T> Result<T> ok(){
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("成功");
        return result;
    }

    //返回失败,无data
    public static<T> Result<T> fail(){
        Result<T> result = new Result<>();
        result.setCode(201);
        result.setMessage("失败");
        return result;
    }*/

    //返回成功,有data
    public static<T> Result<T> ok(T data){
        Result<T> result = new Result<>();
        if (data != null){
            result.setData(data);
        }
        result.setCode(20000);
        result.setMessage("成功");
        return result;
    }

    //返回失败,有data
    public static<T> Result<T> fail(T data){
        Result<T> result = new Result<>();
        if (data != null){
            result.setData(data);
        }
        result.setCode(20001);
        result.setMessage("失败");
        return result;
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }



}
