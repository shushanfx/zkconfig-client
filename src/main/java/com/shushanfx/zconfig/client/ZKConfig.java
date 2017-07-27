package com.shushanfx.zconfig.client;

import java.util.List;
import java.util.Map;

/**
 * 用于获取配置信息的的载体，可以通过该载体读取配置信息。
 * Created by shushanfx on 2017/6/12.
 */
public interface ZKConfig {
    Object getValue(String path);
    Integer getInteger(String path);
    int getInteger(String path, int defaultValue);
    Boolean getBoolean(String path);
    boolean getBoolean(String path, boolean defaultValue);
    String getString(String path);
    String getString(String path, String defaultValue);
    Double getDouble(String path);
    double getDouble(String path, double defaultValue);
    List getList(String path, List defaultValue);
    List getList(String path);
    void setContent(String content);
    String getContent();
}
