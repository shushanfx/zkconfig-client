package com.shushanfx.zconfig.client;

/**
 * Created by dengjianxin on 2017/6/12.
 */
public abstract class AbstractZNodeConfig implements ZNodeConfig {
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
}
