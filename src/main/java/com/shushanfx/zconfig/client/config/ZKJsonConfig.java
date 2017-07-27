package com.shushanfx.zconfig.client.config;

import com.shushanfx.zconfig.client.ZKConfig;
import com.shushanfx.zconfig.client.ZKConfigParser;
import com.shushanfx.zconfig.client.util.JSONUtils;

import java.util.List;
import java.util.Map;

/**
 * json config parse for zkconfig.
 * Created by shushanfx on 2017/7/27.
 */
public class ZKJsonConfig extends AbstractZKConfig implements ZKConfigParser {
    private Map<String, Object> map = null;

    @Override
    public Object getValue(String path) {
        if(map!=null && path !=null){
            String[] paths = path.split("\\.");
            Map<String, Object> temp = this.map;
            for(int i = 0; i < paths.length; i++){
                Object value = null;
                if(temp!=null){
                    value = temp.get(paths[i]);
                }
                if(i == paths.length - 1){
                    return value;
                }
                else{

                }
            }
        }
        return null;
    }

    @Override
    public Integer getInteger(String path) {
        return null;
    }

    @Override
    public Boolean getBoolean(String path) {
        return null;
    }

    @Override
    public String getString(String path) {
        return null;
    }

    @Override
    public Double getDouble(String path) {
        return null;
    }

    @Override
    public List getList(String path) {
        return null;
    }

    @Override
    public ZKConfig parse(String content) {
        if (content != null) {
            // may be exception.
            Map<String, Object> tempObject = JSONUtils.unserialize(content);
            this.map = tempObject;
        }
        else{
            this.map = null;
        }
        return this;
    }
}
