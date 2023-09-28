package com.nvyao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer code;   //响应码，1 代表成功； 0 代表失败
    private String msg;     //响应信息，描述字符串
    private Object data;    //返回的数据

    //增删改的成功响应方法
    public static Result success(){
        return new Result(1, "success", null);
    }

    //查询成功响应的方法
    public static Result success(Object data){
        return new Result(1, "success", data);
    }

    //失败响应，失败响应必须指定msg
    public static Result error(String msg){
        return new Result(0, msg, null);
    }

}
