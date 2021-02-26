package edu.sulymka.registerofgraduates.controller;

import edu.sulymka.registerofgraduates.model.Work;
import edu.sulymka.registerofgraduates.service.GraduatedService;
import edu.sulymka.registerofgraduates.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/works")
public class WorkController {
    private final WorkService workService;
    private final GraduatedService graduatedService;

    @Autowired
    public WorkController(WorkService workService, GraduatedService graduatedService) {
        this.workService = workService;
        this.graduatedService = graduatedService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Work>> getAllUser(){
        List<Work> works = workService.getAll();
        return new ResponseEntity<>(works, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Work> getUserById(@PathVariable("id") Long id){
        Work works = workService.readById(id);
        return new ResponseEntity<>(works, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Work> addUser(@RequestBody Work work){
        Work newWork = workService.create(work);
        return new ResponseEntity<>(newWork, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Work> updateUser(@RequestBody Work work){
        Work updateWork = workService.update(work);
        return new ResponseEntity<>(updateWork, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        workService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
