package com.sky.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * ClassName: test
 * Package: cn.nanchengyu
 * Description:
 *
 * @Author 南城余
 * @Create 2023/12/25 16:22
 * @Version 1.0
 */
//@SpringBootTest 测试完可以注释起来 因为以后每次启动main方法时会执行下这个单元测试，影响项目启动速度
public class SpringDataRedisTest {

    @Resource
    private RedisTemplate redisTemplate;


    @Test
    void testRedisTemplate() {

        System.out.println(redisTemplate);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        HashOperations hashOperations = redisTemplate.opsForHash();
        ListOperations listOperations = redisTemplate.opsForList();
        SetOperations setOperations = redisTemplate.opsForSet();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

    }

    @Test
    public void testString() {
        //set get setex setnx

        redisTemplate.opsForValue().set("name", "nanchengyu");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
        redisTemplate.opsForValue().set("code", "123456", 6, TimeUnit.MINUTES);

        redisTemplate.opsForValue().setIfAbsent("lock", "1");


    }


    @Test
    public void testCommon(){
        Set keys = redisTemplate.keys("*");
        for (Object key : keys) {
            System.out.println(key);
        }
        Boolean name = redisTemplate.hasKey("name");
        System.out.println(name);
    }
}



