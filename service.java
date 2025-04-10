package com.example.demo.services;

import com.example.demo.entity.DataEntry;
import com.example.demo.entity.User;
import com.example.demo.repository.Repo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class service {
        //controller ----> Services ------> Repository
    @Autowired
    private Repo repository;

    @Autowired
    private UserService userService;

    public  void saveEntry(DataEntry dataEntry, String userName){
        User user= userService.findByUserName(userName);
        dataEntry.setDate(LocalDateTime.now() );
        DataEntry saved = repository.save(dataEntry);
        user.getEntries().add(saved);
        userService.saveEntry(user);
    }

    public  void saveEntry(DataEntry dataEntry){
        repository.save(dataEntry);
    }

    public List<DataEntry>getAll(){
        return repository.findAll();
    }

    public Optional<DataEntry> findById(ObjectId id){
        return  repository.findById(id);
    }

    public  void deleteEntry(ObjectId id, String userName){
        User user= userService.findByUserName(userName);
        user.getEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        repository.deleteById(id);
    }
}
