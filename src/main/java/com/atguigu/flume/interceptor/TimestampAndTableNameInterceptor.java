package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class TimestampAndTableNameInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {

        byte[] body = event.getBody();
        Map<String, String> headers = event.getHeaders();
        String jsonStr = new String(body, StandardCharsets.UTF_8);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String ts = jsonObject.getString("ts");
        String tableName = jsonObject.getString("table");
        headers.put("timestamp", ts + "000");
        headers.put("tableName", tableName);
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {

        @Override
        public Interceptor build() {
            return new TimestampAndTableNameInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
