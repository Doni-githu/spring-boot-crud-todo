package uz.doniyor.springbootmongodb.models;

import lombok.Setter;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
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
    
    @NotNull(message = "todo cannot be null")
    private String todo;
    
    @NotNull(message = "description cannot be null")
    private String description;
    
    @NotNull(message = "completed field cannot be null")
    private Boolean comlpeted;
    
    private Date createdAt;
    
    private Date updatedAt;
}
