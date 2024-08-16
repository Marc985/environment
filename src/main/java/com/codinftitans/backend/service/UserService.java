package com.codinftitans.backend.service;

import com.codinftitans.backend.dto.RegisterDTO;
import com.codinftitans.backend.model.LoginRequest;
import com.codinftitans.backend.model.User;
import com.codinftitans.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AuthenticationManager authenticationManager;
    public String deleteUser(UUID id){
        userRepository.deleteById(id);
        return "deleted sucessfully";
    }
    public String register(RegisterDTO register){
        User userToCreate=modelMapper.map(register,User.class);
        userToCreate.setPassword("{noop}"+register.getPassword());
        userRepository.save(userToCreate);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(register.getEmail(), register.getPassword()));

        return tokenService.generateToken(authentication);
    }

    /*public User newUser(UserRequestDTO user){
        //String encodedPassword=passwordEncoder.encode(user.getPassword());
        user.setPassword("{noop}"+user.getPassword());
        User userToSave=modelMapper.map(user,User.class);

        return userRepository.save(userToSave);

    }
    public List<UserResponseDTO> findAll(){
        return  userRepository.findAll().stream().map(
                user -> modelMapper.map(user,UserResponseDTO.class)
        ).toList();
    }*/
}
