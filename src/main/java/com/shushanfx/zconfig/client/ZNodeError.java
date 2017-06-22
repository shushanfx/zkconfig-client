package com.shushanfx.zconfig.client;

/**
 * Created by shushanfx on 2017/6/22.
 */
public class ZNodeError {
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

    public ZNodeError() {
    }

    public ZNodeError(int code, String message){
        this.code = code;
        this.message = message;
    }
}
