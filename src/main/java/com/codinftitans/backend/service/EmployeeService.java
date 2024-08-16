package com.codinftitans.backend.service;

import com.codinftitans.backend.dto.CreateEmployeeDTO;
import com.codinftitans.backend.dto.GetEmployeeDTO;
import com.codinftitans.backend.model.Mail;
import com.codinftitans.backend.model.User;
import com.codinftitans.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class EmployeeService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;
    @Autowired
    S3Service s3Service;
    @Transactional
    public CreateEmployeeDTO createEmployee(CreateEmployeeDTO employee, MultipartFile image){
        User employeToCreate=modelMapper.map(employee,User.class);
        String generatedPassword= UUID.randomUUID().toString();
        employeToCreate.setPassword("{noop}"+generatedPassword);
        String textToSend="Bonjour,Voici votre identifiant pour l'authentification:"+employee.getEmail()+" mot de passe:"+generatedPassword;
        System.out.println("email :"+employee.getEmail());
        try {
           String mess= mailService.sendMessage(employee.getEmail(), textToSend);
            System.out.println("ito le message"+mess);
        } catch (Exception e) {
            e.printStackTrace(); // Log l'erreur pour déboguer
            throw new RuntimeException("Échec de l'envoi de l'email", e); // Propager l'erreur pour traitement ultérieur
        }
        if (image != null && !image.isEmpty()) {
            try {
                String fileName = s3Service.uploadFile(image.getOriginalFilename().replace(" ", ""), image);
                employeToCreate.setImage(fileName); // Enregistrez le chemin ou l'URL de l'image
            } catch (IOException e) {
                throw new RuntimeException("Échec de l'enregistrement de l'image", e);
            }
        }

        // Sauvegarder l'utilisateur dans la base de données
        userRepository.save(employeToCreate);
        return employee;
    }
    public List<GetEmployeeDTO> getEmployee(){
        List<User> users=userRepository.findByRole("employee");
        List<GetEmployeeDTO>employees=users.stream().map(user -> modelMapper.map(user,GetEmployeeDTO.class)).toList();
        return employees;
    }
    public GetEmployeeDTO getEmployeeById(UUID id){
        Optional<User> user=userRepository.findById(id);
        GetEmployeeDTO getEmployee=modelMapper.map(user,GetEmployeeDTO.class);
        return getEmployee;
    }
}
