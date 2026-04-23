package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //cause we are already handling this null in Impl (current = 0)
    public String get(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value != null ? value.toString() : null;
    }

    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public boolean isExists(String key){
        return redisTemplate.hasKey(key);
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }
    public void setWithTTL(String key, String value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    //For Notification
    public void pushToList(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }
    public List<String> getList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }


    public void decreamentKey(String key){
        redisTemplate.opsForValue().decrement(key);
    }






    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}

