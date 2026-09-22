package com.rupyy.vehicle.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class RedisService {
    private final ObjectMapper objectMapper;
    private final RedisTemplate<Integer, Object> redisTemplate;
    public RedisService(
            ObjectMapper objectMapper,
            RedisTemplate<Integer, Object> redisTemplate) {
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
    }

    public <T> T get(Integer key, Class<T> entityClass) {
        try {
            Object value = redisTemplate.opsForValue().get(key);
            if (value == null) {
                return null;
            }
            return objectMapper.convertValue(value, entityClass);
        } catch (Exception e) {
            log.error("Exception while getting key {}", key, e);
            return null;
        }
    }

    public void set(Integer key, Object value, Long ttl) {
        try {
            redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }


}