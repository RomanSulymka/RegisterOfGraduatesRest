package edu.sulymka.registerofgraduates.controller;

import edu.sulymka.registerofgraduates.model.Work;
import edu.sulymka.registerofgraduates.repository.GraduatedRepository;
import edu.sulymka.registerofgraduates.repository.WorkRepository;
import edu.sulymka.registerofgraduates.service.GraduatedService;
import edu.sulymka.registerofgraduates.service.WorkService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class WorkController {
    private final WorkService workService;
    private final GraduatedService graduatedService;
    private final GraduatedRepository graduatedRepository;
    private final WorkRepository workRepository;

    @Autowired
    public WorkController(WorkService workService, GraduatedService graduatedService, GraduatedRepository graduatedRepository, WorkRepository workRepository) {
        this.workService = workService;
        this.graduatedService = graduatedService;
        this.graduatedRepository = graduatedRepository;
        this.workRepository = workRepository;
    }

    @GetMapping("/works/all")
    public ResponseEntity<List<Work>> getAllWorks(){
        List<Work> works = workService.getAll();
        return new ResponseEntity<>(works, HttpStatus.OK);
    }

    @GetMapping("/works/byCompany")
    public ResponseEntity<List<Work>> getWorksCountByCompany(){
        List<Work> works = workService.getWorksByCompany();
        return new ResponseEntity<>(works, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Work> getWorkById(@PathVariable("id") Long id){
        Work works = workService.readById(id);
        return new ResponseEntity<>(works, HttpStatus.OK);
    }

    @PostMapping("/graduates/{graduatesId}/add")
    public ResponseEntity<Work> addWork(@PathVariable Long graduatesId,
                                        @Valid @RequestBody Work work){
        Work newWork = workService.create(work);
        newWork.setGraduated(graduatedService.readById(graduatesId));
        newWork.setCompany(newWork.getCompany());
        newWork.setPosition(newWork.getPosition());
        return new ResponseEntity<>(workRepository.save(newWork), HttpStatus.OK);
    }


    @PutMapping("/graduates/{graduatesId}/works/{worksId}")
    public Work updateWork(@PathVariable Long graduatesId,
                           @PathVariable Long worksId,
                           @Valid @RequestBody Work work) throws NotFoundException {

        if (!graduatedRepository.existsById(graduatesId)){
            throw new NotFoundException("Work is not found!");
        }

        return workRepository.findById(worksId)
                .map(newWork -> {
                    newWork.setPosition(work.getPosition());
                    newWork.setCompany(work.getCompany());
                    newWork.setStartWork(work.getStartWork());
                    newWork.setEndWork(work.getEndWork());
                    return workRepository.save(newWork);
                }).orElseThrow(() -> new NotFoundException("Work not found!"));
    }

    @DeleteMapping("/graduates/{graduatesId}/delete/{id}")
    public String deleteWork(@PathVariable Long graduatesId,
                                        @PathVariable("id") Long id) throws NotFoundException {
        if (!graduatedRepository.existsById(graduatesId)){
            throw new NotFoundException("Graduate is not found!");
        }
        return workRepository.findById(id)
                .map(assignment -> {
                    workRepository.delete(assignment);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Contact not found!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        workService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
