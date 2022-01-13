package com.sluice.data;

public class BaseInfo {
    private Integer code;

    private String msg;

    public BaseInfo() {}

    public BaseInfo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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

    @Override
    public String toString() {
        return "BaseInfo{" + "code=" + code + ", msg='" + msg + '\'' + '}';
    }
}
