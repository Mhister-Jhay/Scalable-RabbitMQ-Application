package com.work.demo.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collation = "work-items")
public class WorkItem {
    @Id
    private String id;
    private Integer value;
    private Boolean processed;
    private Integer result;
}
