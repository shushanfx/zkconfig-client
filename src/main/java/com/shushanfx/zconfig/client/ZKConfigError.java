package com.shushanfx.zconfig.client;

/**
 * Created by shushanfx on 2017/6/22.
 */
public enum ZKConfigError {
    ERROR_CONNECT(1, "CONNECT FAIL"),
    ERROR_READ(2, "READ ERROR"),
    ERROR_PARSE(4, "PARSE ERROR"),
    ERROR_MONITOR(8, "MONITOR ERROR"),
    ERROR_HANDLE(16, "HANDLE ERROR");

    private int code = 0;
    private String message = null;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ZKConfigError(int code, String message){
        this.code = code;
        this.message = message;
    }
}
