package com.shushanfx.zconfig.client.listener;

import com.shushanfx.zconfig.client.ZKConfigError;

/**
 * Created by shushanfx on 2017/6/22.
 */
public abstract class ZKConfigDataListener implements ZKConfigListener {
    @Override
    public void connected() {

    }

    @Override
    public void error(ZKConfigError error, Throwable e) {

    }
}
