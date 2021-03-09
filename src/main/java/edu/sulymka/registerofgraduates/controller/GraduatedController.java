package edu.sulymka.registerofgraduates.controller;

import edu.sulymka.registerofgraduates.model.Graduated;
import edu.sulymka.registerofgraduates.service.GraduatedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/graduates")
public class GraduatedController {
    private final GraduatedService graduatedService;

    public GraduatedController(GraduatedService graduatedService) {
        this.graduatedService = graduatedService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Graduated>> getAllGraduates(){
        List<Graduated> graduates = graduatedService.getAll();
        return new ResponseEntity<>(graduates, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Graduated> getGraduatedById(@PathVariable("id") Long id){
        Graduated graduated = graduatedService.readById(id);
        return new ResponseEntity<>(graduated, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Graduated> addGraduated(@RequestBody Graduated graduated){
        Graduated newGraduated = graduatedService.addGraduated(graduated);
        return new ResponseEntity<>(newGraduated, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Graduated> updateGraduated(@RequestBody Graduated graduated){
        Graduated updateGraduated = graduatedService.updateGraduated(graduated);
        return new ResponseEntity<>(updateGraduated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGraduated(@PathVariable("id") Long id){
        graduatedService.deleteGraduated(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
