package com.work.demo.domain.events;

import com.google.gson.Gson;
import com.work.demo.domain.model.WorkItem;
import com.work.demo.domain.repository.WorkItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkEventHandler {
    private static final Gson GSON = new Gson();
    private final WorkItemRepository workItemRepository;
    public void handleWorkItem(String workItemDetails){
        WorkItem workItemMessage = GSON.fromJson(workItemDetails, WorkItem.class);
        WorkItem workItem = workItemRepository.findById(workItemMessage.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        workItem.setResult(workItem.getValue()*workItem.getValue());
        workItem.setProcessed(true);
        workItemRepository.save(workItem);
        System.out.println("Processed Successfully");
    }
}
