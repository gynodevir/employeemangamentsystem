package com.gynnodevil.backend_new.controller;

import com.gynnodevil.backend_new.exception.UserNotFoundException;
import com.gynnodevil.backend_new.model.User;
import com.gynnodevil.backend_new.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {
    @Autowired
    private UserRepository userRepository;
   @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }
    @GetMapping("/users")
    List<User> getAllUsers(){
       return userRepository.findAll();
    }
    @GetMapping("/user/{id}")
    User getUserbyId(@PathVariable Long id){
       return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }
//@GetMapping("/user/{id}")
//public ResponseEntity<User> getUserById(@PathVariable Long id){
//    Optional<User> userOptional = userRepository.findById(id);
//    if (userOptional.isPresent()) {
//        return ResponseEntity.ok(userOptional.get());
//    } else {
//        return ResponseEntity.notFound().build();
//    }
//}
  @PutMapping("/user/{id}")
  User updateUser(@RequestBody User newUser,@PathVariable Long id){
       return userRepository.findById(id).map(user->{
           user.setName(newUser.getName());
           user.setEmail(newUser.getEmail());
           user.setUsername(newUser.getUsername());
           return userRepository.save(user);
       }).orElseThrow(()->new UserNotFoundException(id));
  }
  @DeleteMapping("/user/{id}")
  String deleteUser(@PathVariable Long id){
       if(!userRepository.existsById(id)){
           throw new UserNotFoundException(id);
       }
       userRepository.deleteById(id);
       return "user with id "+id+" has been deleted sucess";
  }


}
