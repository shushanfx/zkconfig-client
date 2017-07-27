package com.shushanfx.zconfig.client.config;

import com.shushanfx.zconfig.client.ZKConfig;

import java.util.List;

/**
 * Created by shushanfx on 2017/6/12.
 */
public abstract class AbstractZKConfig implements ZKConfig {
    private String content = null;

    @Override
    public int getInteger(String path, int defaultValue) {
        Integer integer = getInteger(path);
        return integer == null ? defaultValue : integer.intValue();
    }

    @Override
    public boolean getBoolean(String path, boolean defaultValue) {
        Boolean b = getBoolean(path);
        return b == null ? defaultValue : b.booleanValue();
    }

    @Override
    public String getString(String path, String defaultValue) {
        String str = getString(path);
        return str == null ? defaultValue : str;
    }

    @Override
    public double getDouble(String path, double defaultValue) {
        Double d = getDouble(path);
        return d == null ? defaultValue : d.doubleValue();
    }

    @Override
    public List getList(String path, List defaultValue) {
        List list = getList(path);
        return list == null ? list : defaultValue;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return this.content;
    }
}
