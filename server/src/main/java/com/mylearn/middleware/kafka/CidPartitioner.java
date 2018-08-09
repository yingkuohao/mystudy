package com.mylearn.middleware.kafka;

import java.util.Map;
import java.util.Random;

import org.apache.kafka.clients.producer.Partitioner;

import org.apache.kafka.common.Cluster;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 18/3/22
 * Time: ����3:19
 * CopyRight: ying
 * Descrption:
 */

public class CidPartitioner implements Partitioner {

    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        /*
              * Cluster arg5��ӡ�������������
              * Cluster(nodes = [172.17.11.11:9092 (id: 0 rack: null), 172.17.11.13:9092 (id: 1 rack: null), 172.17
              * .11.15:9092 (id: 2 rack: null)], partitions = [Partition(topic = TOPIC-20160504-1200, partition = 1,
              * leader = 2, replicas = [0,1,2,], isr = [2,1,0,], Partition(topic = TOPIC-20160504-1200, partition =
              * 2, leader = 0, replicas = [0,1,2,], isr = [0,2,1,], Partition(topic = TOPIC-20160504-1200, partition
              * = 0, leader = 1, replicas = [0,1,2,], isr = [1,0,2,]])
             */
             /*
              * byte[] arg2���ֽ���ĸ�ʽ�洢key
              * System.out.println(new String(arg2));
              * System.out.println(key.toString());���������ͬ������key
              * byte[] arg4��Object valueͬ��
              */
             /*
              * ����ֵָ���ķ���ֵ
              */
        Random random = new Random();
        int partition = random.nextInt(10); //�ָ� " . " ��Ҫת��" \\. "
        System.out.println("partion=" + partition);
        if (partition % 2 == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
