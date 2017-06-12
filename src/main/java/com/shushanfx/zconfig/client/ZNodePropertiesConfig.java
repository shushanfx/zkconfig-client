package com.shushanfx.zconfig.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.util.Properties;

/**
 * Created by dengjianxin on 2017/6/12.
 */
public class ZNodePropertiesConfig extends AbstractZNodeConfig implements ZNodeParser {
    private static final Logger logger = LoggerFactory.getLogger(ZNodePropertiesConfig.class);
    Properties properties = null;

    @Override
    public Object getValue(String path) {
        return properties.get(path);
    }

    @Override
    public Integer getInteger(String path) {
        String value = properties.getProperty(path);
        if(value!=null){
            try{
                return Integer.parseInt(value);
            } catch (Exception e){
                logger.error("Can not parse " + value + " to int.", e);
            }
        }
        return null;
    }

    @Override
    public Boolean getBoolean(String path) {
        String value = properties.getProperty(path);
        if(value!=null){
            if("1".equals(value)
                    || "true".equalsIgnoreCase(value)
                    || "on".equalsIgnoreCase(value)){
                return true;
            }
            else if("0".equalsIgnoreCase(value)
                    || "false".equalsIgnoreCase(value)
                    || "off".equalsIgnoreCase(value))
                return false;
        }
        return null;
    }

    @Override
    public String getString(String path) {
        return properties.getProperty(path);
    }

    @Override
    public Double getDouble(String path) {
        String value = getString(path);
        if(value!=null){
            try{
                return Double.parseDouble(value);
            } catch (Exception e){
                logger.error("Can not parse " + value + " to double.", e);
            }
        }
        return null;
    }

    @Override
    public ZNodeConfig parse(String content) {
        properties = new Properties();
        if(content != null){
            try{
                properties.load(new StringReader(content));
            }
            catch (Exception e){
                logger.error("parse error!", e);
            }
        }
        return this;
    }
}
