package com.codinftitans.backend.service;

import com.codinftitans.backend.dto.MostActUser;
import com.codinftitans.backend.dto.RegisterDTO;
import com.codinftitans.backend.model.LoginRequest;
import com.codinftitans.backend.model.User;
import com.codinftitans.backend.repository.UserRepository;
import jakarta.persistence.Tuple;
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

    public String deleteUser(UUID id) {
        userRepository.deleteById(id);
        return "deleted sucessfully";
    }

    public String register(RegisterDTO register) {
        User userToCreate = modelMapper.map(register, User.class);
        userToCreate.setPassword("{noop}" + register.getPassword());
        userToCreate.setRole("clients" +
                "");
        userRepository.save(userToCreate);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(register.getEmail(), register.getPassword()));

        return tokenService.generateToken(authentication);
    }

    public MostActUser getMostActifUser() {
        Tuple tuple = userRepository.getTheMostActifUser();

        MostActUser mostActUser = new MostActUser(
                tuple.get("userId", UUID.class),
                tuple.get("email", String.class),
                tuple.get("name", String.class),
                tuple.get("telephone", String.class),
                tuple.get("image", String.class),
                tuple.get("plantCount", Long.class)
        );

        return mostActUser;
    }

}