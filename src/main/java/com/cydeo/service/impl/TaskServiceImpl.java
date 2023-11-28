package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
        Optional<Task> task = taskRepository.findById(dto.getId());
        Task convertedTask = taskMapper.convertToEntity(dto);

        if(task.isPresent()){
            convertedTask.setId(task.get().getId());
            convertedTask.setTaskStatus(dto.getTaskStatus()==null ? task.get().getTaskStatus():dto.getTaskStatus());
            convertedTask.setAssignedDate(task.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }
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
        listAllByProject(projectDTO).forEach(t->delete(t.getId()));
    }

    @Override
    public void completeByProject(ProjectDTO projectDTO) {
        List<TaskDTO> tasks = listAllByProject(projectDTO);
        tasks.forEach(t->{
            t.setTaskStatus(Status.COMPLETE);
            update(t);
            });
    }

    @Override
    public List<TaskDTO> listAllTaskByStatusIsNot(Status status) {
        List<TaskDTO> tasks = findAllTasksByEmployee("john@employee.com");
        return tasks.stream().filter(t->!t.getTaskStatus().equals(status)).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO dto) {
        Optional<Task> task = taskRepository.findById(dto.getId());
        if(task.isPresent()){
            task.get().setTaskStatus(dto.getTaskStatus());
            taskRepository.save(task.get());
        }
    }
    @Override
    public List<TaskDTO> listAllTasksByStatus(Status status) {
        List<TaskDTO> tasks = findAllTasksByEmployee("john@employee.com");
        return tasks.stream().filter(t->t.getTaskStatus().equals(status)).collect(Collectors.toList());
    }
    @Override
    public List<TaskDTO> listAllTasksByEmployee(User user) {
        return findAllTasksByEmployee(user.getUserName());
    }

    private List<TaskDTO> listAllByProject(ProjectDTO dto){
        List<Task> tasks = taskRepository.findAllByProject(projectMapper.convertToEntity(dto));
        return tasks.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    private List<TaskDTO> findAllTasksByEmployee(String username){
        return taskRepository.findAllByAssignedEmployeeUserName(username).stream().map(taskMapper::convertToDto)
                .collect(Collectors.toList());
    }


}
