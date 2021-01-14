package com.practice.test.rabbitmq.service.impl;

import com.practice.test.rabbitmq.component.MessageSender;
import com.practice.test.rabbitmq.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageSender messageSender;

    @Override
    public void generateMessage() {
        log.info("Generate message...");
        // 延迟 15s
        messageSender.sendMessage(11L, 15 * 1000);
    }

    @Override
    public void customerMessage(Long id) {
        log.info("Customer message id:{}", id);
    }
}
