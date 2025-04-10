package com.example.demo.controller;

import com.example.demo.entity.DataEntry;
import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import com.example.demo.services.service;
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
    private service serve;

    @Autowired
    private UserService userService;


    @GetMapping("/userName")
    public ResponseEntity<?>getAllDataEntries(@PathVariable String userName){
        User user= userService.findByUserName(userName);
        List<DataEntry> all=user.getEntries();
        if((all != null) && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<DataEntry> post(@RequestBody DataEntry entry,@PathVariable String userName){
        try {
            serve.saveEntry(entry,userName);
            return new ResponseEntity<>(entry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myID}")
    public ResponseEntity<DataEntry> getId(@PathVariable ObjectId myID){
         Optional<DataEntry> entry= serve.findById(myID);
         if(entry.isPresent()){
             return new ResponseEntity<>(entry.get(), HttpStatus.OK);
         }return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?>delete(@PathVariable String userName,@PathVariable ObjectId myId) {
       serve.deleteEntry(myId,userName );
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<DataEntry> put(@PathVariable ObjectId id,
                                         @RequestBody DataEntry entry){
        DataEntry oldEntry= serve.findById(id).orElse(null);
        if(oldEntry!=null){
            oldEntry.setName(entry.getName());
            oldEntry.setAddress(entry.getAddress());
            serve.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
