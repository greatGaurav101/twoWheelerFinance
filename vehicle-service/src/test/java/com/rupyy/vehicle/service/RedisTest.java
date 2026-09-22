package com.rupyy.vehicle.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redisTemplate(){
        redisTemplate.opsForValue().set("name","amit");
        Object name = redisTemplate.opsForValue().get("name");
        String str = "akash";
    }


}
