package com.shushanfx.zconfig.client.parser;

import com.shushanfx.zconfig.client.config.ZNodeConfig;

/**
 * Parse content to a ZNodeConfig instance.
 * Created by shushanfx on 2017/6/12.
 */
public interface ZNodeParser {
    /**
     * Parse a string content to a zNodeConfig instance.
     * @param content The value to parse. it can be null.
     * @return a ZNodeConfig instance, it can be null.
     */
    ZNodeConfig parse(String content);
}
