package com.work.demo.application.controller;

import com.work.demo.application.model.WorkItemRequest;
import com.work.demo.domain.service.WorkItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkItemController {
    private final WorkItemService workItemService;

    @PostMapping("/save-work-item")
    public ResponseEntity<String> saveWorkItem(@RequestBody WorkItemRequest request){
        return new ResponseEntity<>(workItemService.createWorkItem(
                request.getValue()), HttpStatus.CREATED);
    }
}
