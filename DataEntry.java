package com.example.demo.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "data_entries")
@Data
@NoArgsConstructor
public class DataEntry {

    @Id
    private ObjectId id;
    @NonNull
    private String name;
    private String Address;
    private LocalDateTime date;
}
