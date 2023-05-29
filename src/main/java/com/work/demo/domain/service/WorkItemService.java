package com.work.demo.domain.service;

import com.work.demo.domain.model.WorkItem;
import com.work.demo.domain.repository.WorkItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkItemService {
    private final WorkItemSender workItemSender;
    private final WorkItemRepository workItemRepository;

    public String createWorkItem(int value){
        if(value < 1 || value > 10){
            throw new IllegalArgumentException("Invalid request");
        }
        WorkItem workItem = new WorkItem();
        workItem.setValue(value);
        workItem.setProcessed(false);
        WorkItem savedWorkItem = workItemRepository.save(workItem);
        workItemSender.sendRegisteredMessage(savedWorkItem);
        return savedWorkItem.getId();
    }
}
