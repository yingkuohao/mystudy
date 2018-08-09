package com.mylearn.middleware.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.alibaba.fastjson.JSONObject;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 18/3/22
 * Time: ÏÂÎç2:21
 * CopyRight: ying
 * Descrption:
 */

public class ProducerTest {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", " com.mylearn.middleware.kafka.CidPartitioner");
        Producer<String, String> producer = new KafkaProducer<>(props);
        String topic = "topic-multi-parti";
        for (int i = 0; i < 100; i++) {
            try {
                //Thread.sleep(1000);
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, Integer.toString(i),
                    Integer.toString(i));
                Future<RecordMetadata> recordMetadataFuture = producer.send(
                    producerRecord);
                RecordMetadata recordMetadata = recordMetadataFuture.get();
                System.out.println("I=" + i +
                    "topic+" + recordMetadata.topic() + ",partion+ " + recordMetadata.partition());
                System.out.println("recordMetadataFuture" + JSONObject.toJSONString(recordMetadata));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        producer.close();
    }
}
