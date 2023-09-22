package uz.doniyor.springbootmongodb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolationException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uz.doniyor.springbootmongodb.exception.TodoCollectionExeption;
import uz.doniyor.springbootmongodb.models.Todo;
import uz.doniyor.springbootmongodb.repositories.TodoRepository;
import uz.doniyor.springbootmongodb.service.TodoService;
import uz.doniyor.springbootmongodb.utils.Message;

@RestController
public class TodoController {

  @Autowired
  private TodoRepository todoRepo;

  @Autowired
  private TodoService todoService;

  private ObjectMapper objectMapper = new ObjectMapper();

  @GetMapping("/todos")
  public ResponseEntity<?> getAllTodos() throws JsonProcessingException {
    List<Todo> todos = todoRepo.findAll();
    if (todos.size() > 0) {
      return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(
          textToJson("No todos are available"),
          HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/todos")
  public ResponseEntity<?> createTodo(@RequestBody Todo todo)
      throws JsonProcessingException, TodoCollectionExeption {
    try {
      todoService.createTodo(todo);
      return new ResponseEntity<String>(
          textToJson("Todo was created"),
          HttpStatus.CREATED);
    } catch (ConstraintViolationException e) {
      return new ResponseEntity<String>(
          textToJson(e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (JsonProcessingException e) {
      return new ResponseEntity<String>(
          textToJson(e.getMessage()),
          HttpStatus.CONFLICT);
    }
  }

  @GetMapping("/todos/{id}")
  public ResponseEntity<?> getOneTodo(@PathVariable("id") String id)
      throws JsonProcessingException {
    Optional<Todo> todo = todoRepo.findById(id);
    if (todo.isPresent()) {
      return new ResponseEntity<>(todo.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<String>(
          textToJson("Todo was not found"),
          HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping(value = "todos/{id}")
  public ResponseEntity<?> updateTodo(
      @PathVariable("id") String id,
      @RequestBody Todo todo) throws JsonProcessingException {
    Optional<Todo> todoOptinal = todoRepo.findById(id);
    if (todoOptinal.isPresent()) {
      Todo todoSave = todoOptinal.get();
      todoSave.setComlpeted(
          todo.getComlpeted() != null
              ? todo.getComlpeted()
              : todoSave.getComlpeted());
      todoSave.setDescription(
          todo.getDescription() != null
              ? todo.getDescription()
              : todoSave.getDescription());
      todoSave.setTodo(
          todo.getTodo() != null ? todo.getTodo() : todoSave.getTodo());
      todoSave.setUpdatedAt(new Date(System.currentTimeMillis()));
      todoRepo.save(todoSave);
      return new ResponseEntity<>(todoSave, HttpStatus.OK);
    } else {
      return new ResponseEntity<String>(
          textToJson("Todo is undefined"),
          HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/todos/{id}")
  public ResponseEntity<?> deleteOneTodo(@PathVariable("id") String id) throws JsonProcessingException {
    try {
      todoRepo.deleteById(id);
      return new ResponseEntity<String>(
          textToJson("Todo was deleted"),
          HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  public String textToJson(String text) throws JsonProcessingException {
    Message message = new Message(text);
    String json = this.objectMapper.writeValueAsString(message);
    return json;
  }
}
