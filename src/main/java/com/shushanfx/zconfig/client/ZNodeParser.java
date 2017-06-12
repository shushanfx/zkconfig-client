package com.shushanfx.zconfig.client;

/**
 * Parse content to a ZNodeConfig instance.
 * Created by dengjianxin on 2017/6/12.
 */
public interface ZNodeParser {
    ZNodeConfig parse(String content);
}
