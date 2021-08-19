package com.todo.todolist.controller;

import com.todo.todolist.model.TodoItem;
import com.todo.todolist.repository.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/todo")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public String getAllTodo(Model model){
        List<TodoItem> findAll = todoRepository.findAll();
        model.addAttribute("todoList", findAll);
        model.addAttribute("index", "todoList");
        return "index";
    }

    @PostMapping("/add-todo")
    public String save(@RequestParam String todoTitle){
        Random random = new Random();
        if(todoTitle != null && !todoTitle.isEmpty()){
            TodoItem todoItem = new TodoItem(random.nextLong(), todoTitle);
            todoRepository.save(todoItem);
        }
        return "redirect:/todo";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id)
    {
        this.todoRepository.deleteById(id);
        return "redirect:/todo";
    }
}
