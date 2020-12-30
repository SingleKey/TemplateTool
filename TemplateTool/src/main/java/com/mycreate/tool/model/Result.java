package com.mycreate.tool.model;

public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public Result() {

    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result(200, msg, data);
    }
    public static <T> Result<T> success(String msg) {
        return new Result(200, msg, null);
    }

    public static <T> Result<T> error(String msg) {
        return new Result(500, msg, null);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result(500, msg, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
