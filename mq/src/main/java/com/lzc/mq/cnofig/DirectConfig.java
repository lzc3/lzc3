package com.lzc.mq.cnofig;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.lzc.mq.constant.MqConstant.*;

@Configuration
public class DirectConfig {

    /**
     * 声明交换机
     * @return Direct类型交换机
     */
    @Bean
    public DirectExchange directExchange(){
        return ExchangeBuilder.directExchange(DIRECT_EXCHANGE_NAME).build();
    }

    /**
     * 第1个队列
     */
    @Bean
    public Queue directQueue1(){

        return QueueBuilder
                .durable(DIRECT_QUEUE_ONE)
                .lazy() // 开启Lazy模式
                .build();
//        return new Queue(DIRECT_QUEUE_ONE);
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindingQueue1WithRed(Queue directQueue1, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue1).to(directExchange).with(DIRECT_QUEUE_ONE_KEY);
    }

    /**
     * 第2个队列
     */
    @Bean
    public Queue directQueue2(){
        return new Queue(DIRECT_QUEUE_TWO);
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindingQueue2WithYellow(Queue directQueue2, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue2).to(directExchange).with(DIRECT_QUEUE_TWO_KEY);
    }
}