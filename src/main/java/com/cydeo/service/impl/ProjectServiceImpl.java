package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO object) {
        if (object.getProjectStatus()==null) object.setProjectStatus(Status.Open);
        return super.save(object.getProjectCode(),object);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {
        if (object.getProjectStatus()==null) object.setProjectStatus(Status.Open);
        super.update(object.getProjectCode(),object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public ProjectDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public void complete(String projectCode) {
        super.findById(projectCode).setProjectStatus(Status.COMPLETE);
    }

    @Override
    public List<ProjectDTO> findAllNonCompletedProjects() {
        return super.findAll().stream().filter(p->!p.getProjectStatus().equals(Status.COMPLETE)).collect(Collectors.toList());
    }


    @Override
    public List<ProjectDTO> getCountedListOfProjectDto(UserDTO manager) {
        return findAll()
                .stream()
                .filter(p->p.getAssignedManager().equals(manager))
                .map(p->{
                    List<TaskDTO> taskList = taskService.findAllTasksByManager(manager);

                    int completeTaskCounts = (int)taskList.stream()
                            .filter(t->t.getProject().equals(p)).filter(t->t.getProject().equals(p)&&t.getTaskStatus()==Status.COMPLETE)
                            .count();

                    int unfinishedTaskCounts = (int)taskList.stream()
                            .filter(t->t.getProject().equals(p)).filter(t->t.getProject().equals(p)&&t.getTaskStatus()!=Status.COMPLETE)
                            .count();

                    p.setCompleteTaskCounts(completeTaskCounts);
                    p.setUnfinishedTaskCounts(unfinishedTaskCounts);
                    return p;
                        }
                ).collect(Collectors.toList());

    }

}
