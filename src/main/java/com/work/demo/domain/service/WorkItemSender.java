package com.work.demo.domain.service;

import com.google.gson.Gson;
import com.work.demo.domain.model.WorkItem;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkItemSender {
    private final RabbitTemplate rabbitTemplate;
    private static final Gson GSON = new Gson();

    public void sendRegisteredMessage(WorkItem workItem){
        rabbitTemplate.convertAndSend("workItemTopic",
                "workItem.process", GSON.toJson(workItem));
    }
}
