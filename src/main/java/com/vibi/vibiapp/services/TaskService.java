package com.vibi.vibiapp.services;
import com.vibi.vibiapp.dao.TaskDao;
import com.vibi.vibiapp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    static List<Task> tasks = new ArrayList<>();
    static {
        tasks.add(new Task(1,"Task1","Task-1 Description","Active"));
        tasks.add(new Task(2,"Task2","Task-2 Description","Active"));
        tasks.add(new Task(3,"Task3","Task-3 Description","Pending"));
        tasks.add(new Task(4,"Task4","Task-4 Description","Active"));
        tasks.add(new Task(5,"Task5","Task-5 Description","Pending"));
    }

    public List<Task> getTasks(){
        return taskDao.getTasks();
    }

    public Task getTask(int id){
        return taskDao.getTaskById(id);
    }

    public String addTask(Task task) {
        return taskDao.addTask(task);
    }

    public String updateTask(int id, Task task) {
       return taskDao.updateTask(id, task);
    }

    public String deleteTask(int id) {
        return taskDao.deleteTask(id);
    }
}
