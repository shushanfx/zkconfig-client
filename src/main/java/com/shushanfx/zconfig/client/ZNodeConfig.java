package com.shushanfx.zconfig.client;

/**
 * 用于获取配置信息的的载体，可以通过该载体读取配置信息。
 * Created by dengjianxin on 2017/6/12.
 */
public interface ZNodeConfig {
    Object getValue(String path);
    Integer getInteger(String path);
    int getInteger(String path, int defaultValue);
    Boolean getBoolean(String path);
    boolean getBoolean(String path, boolean defaultValue);
    String getString(String path);
    String getString(String path, String defaultValue);
    Double getDouble(String path);
    double getDouble(String path, double defaultValue);
}
