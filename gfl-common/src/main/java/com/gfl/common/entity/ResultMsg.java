package com.gfl.common.entity;



import com.gfl.common.enums.ExtensibleEnum;
import com.gfl.common.enums.ResultCode;

import java.io.Serializable;

/**
 * @author LIUKANGJIN
 */
public class ResultMsg<T> implements Serializable {

    private int code;
    private String msg;
    private T data = null;

    public static <T> ResultMsg<T> create(){
        return new ResultMsg<T>();
    }

    public static <T> ResultMsg<T> ok(){
        return ResultMsg.create().status(ResultCode.OK);
    }

    public static <T> ResultMsg<T> ok(T data){
        return ResultMsg.ok().data(data);
    }

    public static <T> ResultMsg<T> error(int code, String msg){
        return ResultMsg.create().code(code).msg(msg);
    }

    public static <T> ResultMsg<T> error(String msg){
        return ResultMsg.create().status(ResultCode.ERROR).msg(msg);
    }

    public static <T> ResultMsg<T> error(ExtensibleEnum status){
        return ResultMsg.create().status(status);
    }

    private ResultMsg(){
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public ResultMsg status(ExtensibleEnum status){
        this.setCode(status.getCode());
        this.setMsg(status.getName());
        return this;
    }

    public ResultMsg code(int code){
        this.code = code;
        return this;
    }

    public ResultMsg msg(String msg){
        this.msg = msg;
        return this;
    }

    public ResultMsg data(T data){
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "code=" + code +
                ", msg=\"" + msg + "\"" +
                ", data=" + data +
                '}';
    }

}
