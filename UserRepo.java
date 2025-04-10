package com.example.sample.repository;
import com.example.sample.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


//controller ----> service ----> repository
@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {

        User findByUserName(String userName);
}
