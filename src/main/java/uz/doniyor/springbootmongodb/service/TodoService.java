package uz.doniyor.springbootmongodb.service;

import jakarta.validation.ConstraintViolationException;
import uz.doniyor.springbootmongodb.exception.TodoCollectionExeption;
import uz.doniyor.springbootmongodb.models.Todo;

public interface TodoService {
    public void createTodo(Todo todo) throws TodoCollectionExeption, ConstraintViolationException;
}
