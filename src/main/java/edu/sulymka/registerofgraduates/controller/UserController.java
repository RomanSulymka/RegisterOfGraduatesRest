package edu.sulymka.registerofgraduates.controller;

import edu.sulymka.registerofgraduates.model.Graduated;
import edu.sulymka.registerofgraduates.model.User;
import edu.sulymka.registerofgraduates.service.RoleService;
import edu.sulymka.registerofgraduates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User users = userService.readById(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

   /* @PostMapping("/create")
    public ResponseEntity<User> addUser(@RequestBody User user){
        user.setRole(roleService.readById(2L));
        User newUser = userService.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }*/

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updateUser = userService.update(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
