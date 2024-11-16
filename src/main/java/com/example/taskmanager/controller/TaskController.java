package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();

    // GET - Listar todas las tareas
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(tasks);
    }

    // POST - Crear una nueva tarea
    @PostMapping
    public ResponseEntity<List<Task>> createTasks(@RequestBody List<Task> taskList) {
        for (Task task : taskList) {
            tasks.add(task);
        }
        return new ResponseEntity<>(taskList, HttpStatus.CREATED);
    }

    // PUT - Marcar una tarea como completada
    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setCompleted(true);
                return ResponseEntity.ok(task);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}