package com.practice.test.rabbitmq.component;

import com.practice.test.rabbitmq.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 */
@Component
@RabbitListener(queues = "demo.cancel")
@Slf4j
public class MessageReceiver {

    @Autowired
    private MessageService messageService;

    @RabbitHandler
    public void handle(Long id) {
        log.info("receive delay message id:{}", id);
        messageService.customerMessage(id);
    }
}
