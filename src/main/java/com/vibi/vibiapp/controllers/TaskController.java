package com.vibi.vibiapp.controllers;

import com.vibi.vibiapp.model.Task;
import com.vibi.vibiapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public ResponseEntity getTasks() {
        List<Task> tasks = taskService.getTasks();
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTask(@PathVariable int id) {
        Task task = taskService.getTask(id);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addTask(@RequestBody Task task) {
        String taskOutput = taskService.addTask(task);
        return new ResponseEntity<String>(taskOutput, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTask(@PathVariable int id, @RequestBody Task task){
       String taskOutput =  taskService.updateTask(id, task);
       return new ResponseEntity<String>(taskOutput, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
