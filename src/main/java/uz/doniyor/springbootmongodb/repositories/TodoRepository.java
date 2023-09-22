package uz.doniyor.springbootmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import uz.doniyor.springbootmongodb.models.Todo;
import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
    @Query("{'todo': ?0}")
    Optional<Todo> findByTodo(String todo);
}
