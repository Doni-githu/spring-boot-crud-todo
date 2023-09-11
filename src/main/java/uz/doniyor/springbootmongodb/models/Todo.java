package uz.doniyor.springbootmongodb.models;

import lombok.Setter;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("todos")
public class Todo {
    @Id
    private String id;
    
    private String todo;
    
    private String description;
    
    private Boolean comlpeted;
    
    private Date createdAt;
    
    private Date updatedAt;
}
