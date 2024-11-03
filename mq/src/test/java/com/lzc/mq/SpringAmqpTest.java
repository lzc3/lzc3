package com.lzc.mq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue() {
        // 队列名称
        String queueName = "lzc-queue";
        // 消息
        // 准备消息
        Map<String,Object> msg = new HashMap<>();
        msg.put("name", "lzc");
        msg.put("age", 24);
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, msg);
    }
}