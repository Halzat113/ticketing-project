package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {
//    TaskService taskService;
//    ProjectService projectService;
//    UserService userService;
//
//    public TaskController(TaskService taskService, ProjectService projectService, UserService userService) {
//        this.taskService = taskService;
//        this.projectService = projectService;
//        this.userService = userService;
//    }
//
//    @GetMapping("/create")
//    public String createTask(Model model){
//        model.addAttribute("task", new TaskDTO());
//        model.addAttribute("tasks",taskService.findAll());
//        model.addAttribute("projects",projectService.findAll());
//        model.addAttribute("employees",userService.findAll());
//        return "/task/create";
//    }
//
//    @PostMapping("/create")
//    public String insertTask(@ModelAttribute("task")TaskDTO task){
//        taskService.save(task);
//        return "redirect:/task/create";
//    }
//
//    @GetMapping("/update/{id}")
//    public String editTask(@PathVariable("id")Long id,Model model){
//        model.addAttribute("task",taskService.findById(id));
//        model.addAttribute("tasks",taskService.findAll());
//        model.addAttribute("projects",projectService.findAll());
//        model.addAttribute("employees",userService.findAll());
//        return "/task/update";
//    }
//
////    @PostMapping("/update/{taskid}")
////    public String updateTask(@ModelAttribute("task")TaskDTO taskDTO,@PathVariable("taskid")Long taskid){
////        taskDTO.setId(taskid);
////        taskService.update(taskDTO);
////        return "redirect:/task/create";
////    }
//    @PostMapping("/update/{id}")
//    public String updateTask(@ModelAttribute("task")TaskDTO taskDTO){
//        taskService.update(taskDTO);
//        return "redirect:/task/create";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteTask(@PathVariable("id")Long id){
//        taskService.deleteById(id);
//        return "redirect:/task/create";
//    }
}
