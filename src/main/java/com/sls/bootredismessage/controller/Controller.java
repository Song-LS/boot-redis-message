package com.sls.bootredismessage.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sls
 * 消息发送者
 **/
@RestController
public class Controller {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /*
    是一个提供原子操作的Integer类，通过线程安全的方式操作加减
     */
    private static AtomicInteger count = new AtomicInteger();

    @PostMapping("/message")
    public void sendMessage() {
        int i = count.incrementAndGet();
        stringRedisTemplate.convertAndSend("topic", "消息" + i);
    }
}
