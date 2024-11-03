package com.lzc.mq.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lzc.mq.constant.MqConstant.*;

/**
 * 直接向队列发送消息
 */
@Service
@Slf4j
public class PublishMessageService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    String message = "hello, spring amqp!";

    public void publishMessageByType(String type) {
        switch (type) {
            case MESSAGE_TO_SIMPLE_QUEUE:
                log.info("直接发送消息到某个队列");
                publishSimpleQueue();
                break;
            case MESSAGE_TO_FANOUT_EXCHANGE:
                log.info("发送消息到Fanout交换机");
                publishMessageToFanoutExchange();
                break;
            case MESSAGE_TO_DIRECT_EXCHANGE:
                log.info("发送消息到Direct交换机");
                publishMessageToDirectExchange();
                break;
            case MESSAGE_TO_TOPIC_EXCHANGE:
                log.info("发送消息到Topic交换机");
                publishMessageToTopicExchange();
                break;
            default:
                break;
        }
    }

    public void publishSimpleQueue() {
        // 发送消息
        rabbitTemplate.convertAndSend(QUEUE_NAME, message);
    }

    public void publishMessageToFanoutExchange() {
        // 发送消息
        rabbitTemplate.convertAndSend(FANOUT_EXCHANGE_NAME, null, message);
    }

    public void publishMessageToDirectExchange() {
        // 发送消息
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, DIRECT_QUEUE_ONE_KEY, message);

//        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_NAME, DIRECT_QUEUE_TWO_KEY, message);
    }

    public void publishMessageToTopicExchange() {
        // 发送消息
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, TOPIC_QUEUE_KEY, message);

    }

}
