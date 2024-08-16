package com.codinftitans.backend.service;

import com.codinftitans.backend.dto.CreateEmployeeDTO;
import com.codinftitans.backend.model.Mail;
import com.codinftitans.backend.model.User;
import com.codinftitans.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class EmployeeService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;
    @Transactional
    public CreateEmployeeDTO createEmployee(CreateEmployeeDTO employee){
        User employeToCreate=modelMapper.map(employee,User.class);
        String generatedPassword= "{noop}"+UUID.randomUUID();
        employeToCreate.setPassword(generatedPassword);
        String textToSend="Bonjour,Voici votre identifiant pour l'authentification:"+employee.getEmail()+" mot de passe:"+generatedPassword;
        mailService.sendMessage(employeToCreate.getEmail(),textToSend);
        userRepository.save(employeToCreate);
        return employee;
    }
}
