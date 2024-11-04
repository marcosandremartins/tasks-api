package com.training.tasks.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    private Long id;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date completionDate;
    private boolean completed;
}
