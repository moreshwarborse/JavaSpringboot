package com.example.demo.controller;

import com.example.demo.entity.DataEntry;
import com.example.demo.repository.Repo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/con")
public class Controller {

//    public  Map<Long, DataEntry> Entry= new HashMap<>();
    @Autowired
    private Repo repository;


    @GetMapping//("/get")
    public ResponseEntity<?>getAll(){
        List<DataEntry> all=repository.findAll();
        if(all != null && all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping//("/post")
    public ResponseEntity<DataEntry> post(@RequestBody DataEntry entry){
        try {
            entry.setDate(LocalDateTime.now());
            repository.save(entry);
            return new ResponseEntity<>(entry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myID}")
    public ResponseEntity<DataEntry> getId(@PathVariable ObjectId myID){
         Optional<DataEntry> entry= repository.findById(myID);
         if(entry.isPresent()){
             return new ResponseEntity<>(entry.get(), HttpStatus.OK);
         }return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("id/{myId}")
    public ResponseEntity<?>delete(@PathVariable ObjectId myId) {
       repository.deleteById(myId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<DataEntry> put(@PathVariable ObjectId id,@RequestBody DataEntry entry){
        DataEntry oldEntry= repository.findById(id).orElse(null);
        if(oldEntry!=null){
            oldEntry.setName(entry.getName());
            oldEntry.setAddress(entry.getAddress());
            repository.save(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
