package com.example.demo;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
@RocketMQMessageListener(topic = "topic",selectorExpression = "tag",consumerGroup = "listener1",messageModel = MessageModel.BROADCASTING)
public class MessageListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String messageExt) {

        try {
            System.out.println("我是listener0:"+messageExt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
