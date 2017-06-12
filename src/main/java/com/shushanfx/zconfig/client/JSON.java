package com.shushanfx.zconfig.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by dengjianxin on 2017/6/12.
 */
public class JSON {
    private static final Gson gson = new Gson();

    public static String serialize(Object obj){
        if(obj == null){
            return "{}";
        }
        return gson.toJson(obj);
    }

    public static Map<String, Object> unserialize(String str){
        if(str!=null && "".equals(str.trim())){
            try{
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                return gson.fromJson(str, type);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
