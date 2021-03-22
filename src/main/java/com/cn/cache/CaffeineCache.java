package com.cn.cache;

import com.alibaba.fastjson.JSONObject;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.ibatis.cache.Cache;

import java.time.Duration;

/**
 * @Classname CaffeineCache
 * @Description TODO
 * @Author Jack
 * Date 2021/3/9 14:38
 * Version 1.0
 */
public class CaffeineCache implements Cache {

    private final String id;

    static com.github.benmanes.caffeine.cache.Cache<String, String> cache = null;

    static {
        cache = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(Long.valueOf(100)))
                .maximumSize(Long.valueOf(10000))
                .initialCapacity(10)
                .build();
    }

    public CaffeineCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        cache.put(JSONObject.toJSONString(key), JSONObject.toJSONString(value));
    }

    @Override
    public Object getObject(Object key) {
        Object parse = JSONObject.parse(cache.getIfPresent(JSONObject.toJSONString(key)));
        return parse;
    }

    @Override
    public Object removeObject(Object key) {
        cache.invalidate(key);
        return null;
    }

    @Override
    public void clear() {
        cache.cleanUp();
    }

    @Override
    public int getSize() {
        return Integer.valueOf(cache.estimatedSize()+"");
    }
}
