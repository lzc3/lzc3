package com.lzc.mq.constant;

public interface MqConstant {

    String QUEUE_NAME = "lzc-queue";

    String FANOUT_QUEUE_ONE = "lzc-fanout-queue-one";
    String FANOUT_QUEUE_TWO = "lzc-fanout-queue-two";

    String FANOUT_EXCHANGE_NAME = "lzc-fanout";


    String DIRECT_QUEUE_ONE = "lzc-direct-queue-one";
    String DIRECT_QUEUE_TWO = "lzc-direct-queue-two";
    String DIRECT_EXCHANGE_NAME = "lzc-direct";

    String DIRECT_QUEUE_ONE_KEY = "one";
    String DIRECT_QUEUE_TWO_KEY = "two";


    String TOPIC_QUEUE = "lzc-topic-queue";
    String TOPIC_EXCHANGE_NAME = "lzc-topic";
    String TOPIC_QUEUE_KEY = "china.weather";

    String MESSAGE_TO_SIMPLE_QUEUE = "MESSAGE_TO_SIMPLE_QUEUE";
    String MESSAGE_TO_FANOUT_EXCHANGE = "MESSAGE_TO_FANOUT_EXCHANGE";

    String MESSAGE_TO_DIRECT_EXCHANGE = "MESSAGE_TO_DIRECT_EXCHANGE";

    String MESSAGE_TO_TOPIC_EXCHANGE = "MESSAGE_TO_TOPIC_EXCHANGE";
}
