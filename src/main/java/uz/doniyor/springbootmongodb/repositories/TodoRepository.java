package uz.doniyor.springbootmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import uz.doniyor.springbootmongodb.models.Todo;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
    
}
