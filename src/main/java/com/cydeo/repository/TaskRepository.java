package com.cydeo.repository;

import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode= ?1 AND t.taskStatus<>'COMPLETE'")
    int totalNonCompletedTask(String code);

    @Query(value = "select count(*) from tasks t join projects p on p.id = t.project_id where p.project_code=?1 and t.task_status='COMPLETE';",nativeQuery = true)
    int totalCompletedTask(String code);

    List<Task>findAllByProject(Project project);

    List<Task> findAllByTaskStatusIsNot(Status status);

    List<Task> findAllByAssignedEmployeeUserName(String username);

    List<Task> findAllByTaskStatus(Status status);
}
