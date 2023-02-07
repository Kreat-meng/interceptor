package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

    public static boolean isJson(String log){
        try {
            JSONObject jsonObject = JSONObject.parseObject(log);
            return true;
        }catch (JSONException e){
            return false;
        }

    }
}
