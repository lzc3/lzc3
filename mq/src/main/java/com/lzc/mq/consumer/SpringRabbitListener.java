package com.lzc.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.lzc.mq.constant.MqConstant.*;

/**
 * 接受队列中的消息
 */
@Component
@Slf4j
public class SpringRabbitListener {
    // 利用RabbitListener来声明要监听的队列信息
    // 将来一旦监听的队列中有了消息，就会推送给当前服务，调用当前方法，处理消息。
    // 可以看到方法体中接收的就是消息体的内容
    @RabbitListener(queues = QUEUE_NAME)
    public void listenSimpleQueueMessage(String msg) throws InterruptedException {
        Thread.sleep(25);
        log.info("接收到队列消息为：{}", msg);
    }

    /**
     * 接受fanout类型交换机的消息
     */
    @RabbitListener(queues = FANOUT_QUEUE_ONE)
    public void listenFanoutExchangeMessageOne(String msg) throws InterruptedException {
        Thread.sleep(25);
        log.info("接收到队列消息为：{}", msg);
    }

    /**
     * 接受fanout类型交换机的消息
     */
    @RabbitListener(queues = FANOUT_QUEUE_TWO)
    public void listenFanoutExchangeMessageTwo(String msg) throws InterruptedException {
        Thread.sleep(25);
        log.info("接收到队列消息为：{}", msg);
    }

    /**
     * 接受direct类型交换机的消息
     */
    @RabbitListener(queues = DIRECT_QUEUE_ONE)
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = DIRECT_QUEUE_ONE),
//            exchange = @Exchange(name = DIRECT_EXCHANGE_NAME, type = ExchangeTypes.DIRECT),
//            key = {DIRECT_QUEUE_ONE_KEY}
//    ))
    public void listenDirectQueue1(String msg){
        System.out.println("消费者1接收到direct.queue1的消息：【" + msg + "】");
    }

    public void listenDirectExchangeMessageOne(String msg) throws InterruptedException {
        Thread.sleep(25);
        log.info("接收到队列消息为：{}", msg);
    }

    /**
     * 接受direct类型交换机的消息
     */
    @RabbitListener(queues = DIRECT_QUEUE_TWO)
    public void listenDirectExchangeMessageTwo(String msg) throws InterruptedException {
        Thread.sleep(25);
        log.info("接收到队列消息为：{}", msg);
    }

    /**
     * 接受topic类型交换机的消息
     */
    @RabbitListener(queues = TOPIC_QUEUE)
    public void listenTopicExchangeMessage(String msg) throws InterruptedException {
        Thread.sleep(25);
        log.info("接收到队列消息为：{}", msg);
    }

}