package com.shushanfx.zconfig.client;

/**
 * 获取ZNode的基本信息，通过ZNodeConfig获取
 * Created by dengjianxin on 2017/6/12.
 */
public interface ZNodeListener {
    /**
     * 配置信息加载、更新时调用的方法，通过config直接获取各配置信息。
     * @param config 配置信息处理对象。
     * @param content 原始的配置信息
     */
    void handle(ZNodeConfig config, String content);
}
