package com.sluice.data;

public class BaseInfo {
    private String msg;

    public BaseInfo() {}

    public BaseInfo(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseInfo{" + "msg='" + msg + '\'' + '}';
    }
}
