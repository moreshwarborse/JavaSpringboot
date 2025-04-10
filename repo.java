package com.example.sample.repository;
import com.example.sample.entity.DataEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//controller ----> service ----> repository
@Repository
public interface repo extends MongoRepository<DataEntry, ObjectId> {


}
