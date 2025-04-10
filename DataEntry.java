package com.example.sample.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "data_entries")
@Data
public class DataEntry {

    @Id
    private ObjectId id;
    @NonNull
    private String name;
    private String Address;
    private LocalDateTime date;



}


