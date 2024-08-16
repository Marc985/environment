package com.codinftitans.backend.controller;

import com.codinftitans.backend.dto.RegisterDTO;
import com.codinftitans.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
  private   UserService userService;
    @PostMapping("/register")
    public String register(@RequestBody  RegisterDTO register){
      return   userService.register(register);
    }
}
