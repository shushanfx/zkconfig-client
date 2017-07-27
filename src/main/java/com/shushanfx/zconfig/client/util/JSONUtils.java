package com.shushanfx.zconfig.client.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by shushanfx on 2017/6/12.
 */
public class JSONUtils {
    private static final Gson gson = new Gson();

    public static String toString(Object obj){
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
