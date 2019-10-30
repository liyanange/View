package com.example.demo;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
//topic selectExpression对应生产者的 consumerGroup随便写
@RocketMQMessageListener(topic = "topic",selectorExpression = "tag",consumerGroup = "listener1",messageModel = MessageModel.BROADCASTING)
public class MessageListener1 implements RocketMQListener<String> {
    @Override
    public void onMessage(String messageExt) {

        try {
            //消费者发生异常或重试 默认是重试16次有一定时间间隔 1s 2S 5s 10s 30S 1min
            String messageStr = messageExt;
            System.out.println("我是listener1"+messageStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
