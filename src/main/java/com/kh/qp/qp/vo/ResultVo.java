package com.kh.qp.qp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID=1l;

    private int code;
    private String  msg;

    private T data;

    private long total;

    public ResultVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVo(int code, String msg, T data, long total) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.total = total;
    }


    public static <T> ResultVo ok(T data) {
        return new ResultVo(0,"操作成功",data);
    }

    public static <T> ResultVo ok(T data,long total) {
        return new ResultVo(0,"操作成功",data,total);
    }


    public static <T> ResultVo ok(String msg) {
        return new ResultVo(0,msg,null);
    }

    public static <T> ResultVo fail(String msg) {
        return new ResultVo(1,msg,null);
    }

    public static <T> ResultVo err(String msg) {
        return new ResultVo(500,msg,null);
    }

}
