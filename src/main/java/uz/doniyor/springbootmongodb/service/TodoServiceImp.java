package uz.doniyor.springbootmongodb.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolationException;
import uz.doniyor.springbootmongodb.exception.TodoCollectionExeption;
import uz.doniyor.springbootmongodb.models.Todo;
import uz.doniyor.springbootmongodb.repositories.TodoRepository;

@Service
public class TodoServiceImp implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void createTodo(Todo todo) throws TodoCollectionExeption, ConstraintViolationException {
        Optional<Todo> todoOptional = todoRepository.findByTodo(todo.getTodo());
        if (todoOptional.isPresent()) {
            throw new TodoCollectionExeption(TodoCollectionExeption.TodoAlreadyExists());
        } else {
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todo.setUpdatedAt(new Date(System.currentTimeMillis()));

            todoRepository.save(todo);
        }
    }
}
