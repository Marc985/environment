package com.codinftitans.backend.controller;


import com.codinftitans.backend.dto.GetClientDTO;
import com.codinftitans.backend.dto.MostActUser;
import com.codinftitans.backend.model.User;
import com.codinftitans.backend.repository.UserRepository;
import com.codinftitans.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
  /*  @GetMapping("/user")
    public ResponseEntity<List<UserResponseDTO> >findAll(){
        List<UserResponseDTO> users=userService.findAll();
        long count=users.size();
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("X-Total-Count",String.valueOf(count));
        return new ResponseEntity<>(users,httpHeaders, HttpStatus.OK);
    }
    @PostMapping("user/new")
    public User newUser(@RequestBody UserRequestDTO user){
     return    userService.newUser(user);
    }
    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable UUID id){
        return userService.deleteUser(id);
    }*/
    @GetMapping("/actif/user")
    public MostActUser actifUser(){
        return userService.getMostActifUser();
    }
    @GetMapping("/users")
    public List<GetClientDTO> users(){
        return userService.getClients();
    }


}
