package com.example.sample.service;

import com.example.sample.entity.DataEntry;

import com.example.sample.repository.repo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class service {
    //controller ----> Services ------> Repository
    @Autowired
    private repo repository;

    public void save(DataEntry dataEntry){
        repository.save(dataEntry);
    }

    public List<DataEntry>getAll(){
        return repository.findAll();
    }

    public Optional<DataEntry> findById(ObjectId id){
        return  repository.findById(id);
    }

    public  void delete(ObjectId id){
        repository.deleteById(id);
    }
    //controller ----> service ----> repository
}
