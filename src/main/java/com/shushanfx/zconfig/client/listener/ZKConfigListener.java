package com.shushanfx.zconfig.client.listener;

import com.shushanfx.zconfig.client.ZKConfigError;
import com.shushanfx.zconfig.client.ZKConfig;

/**
 * ZKConfigListener node listener.
 * Created by shushanfx on 2017/6/22.
 */
public interface ZKConfigListener {
    /**
     * 成功获取数据处理函数。Successfully get the data(including changed data)
     * @param config
     */
    void handle(ZKConfig config);

    /**
     * 连接成功, connect to the server successfully.
     */
    void connected();

    /**
     * 发生错误, an error occur.
     * @param error
     */
    void error(ZKConfigError error, Throwable e);
}
