package com.shushanfx.zconfig.client;

/**
 * Parse content to a ZKConfig instance.
 * Created by shushanfx on 2017/6/12.
 */
public interface ZKConfigParser {
    /**
     * Parse a string content to a ZKConfig instance.
     * @param content The value to parse. it can be null.
     * @return a ZKConfig instance, it can be null.
     */
    ZKConfig parse(String content);
}
