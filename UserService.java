package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    //controller ----> Services ------> Repository
    @Autowired
    private UserRepo UserRepository;

    public void saveEntry(User dataEntry){
        UserRepository.save(dataEntry);
    }

    public List<User>getAll(){
        return UserRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return UserRepository.findById(id);
    }

    public  void delete(ObjectId id){
        UserRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return UserRepository.findByUserName(userName);
    }

    //controller ----> service ----> repository
}
