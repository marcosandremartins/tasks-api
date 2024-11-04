package com.training.tasks.api.repository;

import com.training.tasks.api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT COALESCE(MAX(t.id), 0) FROM Task t")
    Long findMaxId();
}
