package com.cs.scu.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

/**
 * Created by lch on 2017/6/18.
 */
public class KafkaConsumerServer implements MessageListener {
    protected final Logger LOG = LoggerFactory.getLogger("kafkaConsumer");

    public void onMessage(ConsumerRecord record) {
        LOG.info("=============kafkaConsumer开始消费=============");
        System.out.print(record);
        String topic = record.topic();
        String key = record.key().toString();
        String value = record.value().toString();
        long offset = record.offset();
        int partition = record.partition();
        LOG.info("-------------topic:"+topic);
        LOG.info("-------------value:"+value);
        LOG.info("-------------key:"+key);
        LOG.info("-------------offset:"+offset);
        LOG.info("-------------partition:"+partition);
        LOG.info("~~~~~~~~~~~~~kafkaConsumer消费结束~~~~~~~~~~~~~");
    }
}
