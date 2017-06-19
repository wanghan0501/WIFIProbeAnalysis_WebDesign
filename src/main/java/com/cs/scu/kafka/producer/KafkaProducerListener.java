package com.cs.scu.kafka.producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;

/**
 * Created by lch on 2017/6/18.
 */
public class KafkaProducerListener implements ProducerListener {
    protected final Logger LOG = LoggerFactory.getLogger("kafkaProducer");
    public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
        LOG.info("==========kafka发送数据成功（日志开始）==========");
        LOG.info("----------topic:"+topic);
        LOG.info("----------partition:"+partition);
        LOG.info("----------key:"+key);
        LOG.info("----------value:"+value);
        LOG.info("----------RecordMetadata:"+recordMetadata);
        LOG.info("~~~~~~~~~~kafka发送数据成功（日志结束）~~~~~~~~~~");
    }

    public void onError(String topic, Integer partition, Object key, Object value, Exception exception){

        LOG.info("==========kafka发送数据错误（日志开始）==========");
        LOG.info("----------topic:"+topic);
        LOG.info("----------partition:"+partition);
        LOG.info("----------key:"+key);
        LOG.info("----------value:"+value);
        LOG.info("----------Exception:"+exception);
        LOG.info("~~~~~~~~~~kafka发送数据错误（日志结束）~~~~~~~~~~");
        exception.printStackTrace();
    }

    public boolean isInterestedInSuccess() {
        LOG.info("///kafkaProducer监听器启动///");
        return true;
    }
}
