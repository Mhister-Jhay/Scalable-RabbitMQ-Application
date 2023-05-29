package com.work.demo.domain.repository;

import com.work.demo.domain.model.WorkItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkItemRepository extends MongoRepository<WorkItem, String> {
}
