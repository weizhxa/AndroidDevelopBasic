package com.yourapplets.androiddevelopbasic.network;

public class BaseResponse {
    private int code; // 返回的code
    private String msg; // message 可用来返回接口的说明
    private Object data; // 具体的数据结果

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
