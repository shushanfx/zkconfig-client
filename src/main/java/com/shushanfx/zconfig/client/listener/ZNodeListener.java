package com.shushanfx.zconfig.client.listener;

import com.shushanfx.zconfig.client.ZNodeError;
import com.shushanfx.zconfig.client.config.ZNodeConfig;

/**
 * ZNodeListener node listener.
 * Created by shushanfx on 2017/6/22.
 */
public interface ZNodeListener {
    /**
     * 成功获取数据处理函数。Successfully get the data(including changed data)
     * @param config
     */
    void handle(ZNodeConfig config);

    /**
     * 连接成功, connect to the server successfully.
     */
    void connected();

    /**
     * 发生错误, an error occur.
     * @param error
     */
    void error(ZNodeError error);
}
