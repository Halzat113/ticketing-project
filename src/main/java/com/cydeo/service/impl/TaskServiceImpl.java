package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectMapper projectMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ProjectMapper projectMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    public TaskDTO findById(Long id) {
       return taskMapper.convertToDto(taskRepository.findById(id).orElseThrow());
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void save(TaskDTO dto) {
        dto.setTaskStatus(Status.Open);
        dto.setAssignedDate(LocalDate.now());
        taskRepository.save(taskMapper.convertToEntity(dto));
    }

    @Override
    public void update(TaskDTO dto) {
        dto.setAssignedDate(taskRepository.findById(dto.getId()).orElseThrow().getAssignedDate());
        taskRepository.save(taskMapper.convertToEntity(dto));
    }

    @Override
    public void delete(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setIsDeleted(true);
        taskRepository.save(task);
    }

    @Override
    public int totalNonCompletedTask(String code) {
       return taskRepository.totalNonCompletedTask(code);
    }

    @Override
    public int totalCompletedTask(String code) {
        return taskRepository.totalCompletedTask(code);
    }

    @Override
    public void deleteByProject(ProjectDTO projectDTO) {
        List<Task> tasks = taskRepository.findAllByProject(projectMapper.convertToEntity(projectDTO));
        List<TaskDTO> taskDTOList = tasks.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
        taskDTOList.forEach(t->delete(t.getId()));
    }


}
