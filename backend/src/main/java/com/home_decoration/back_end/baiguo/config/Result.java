package com.home_decoration.back_end.baiguo.config;
import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result success(){
        return result(200,"操作成功",null);
    }
    public static Result success(Object data){
        return result(200,"操作成功",data);
    }

    public static Result fail(int code,String msg){
        return result(code,msg,null);
    }
    public static Result fail(int code,String msg,Object data){
        return result(code,msg,data);
    }

    private static Result result(int code, String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return  result;
    }
}