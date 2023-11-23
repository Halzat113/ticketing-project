package com.cydeo.repository;

import com.cydeo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode=?1 AND t.taskStatus<>'Completed'")
    int totalNonCompletedTask(String code);

    @Query(value = "select count(*) from tasks t join projects p on p.id = t.project_id where p.project_code=?1 and t.task_status='COMPLETE';",nativeQuery = true)
    int totalCompletedTask(String code);
}
