package com.codinftitans.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEmployeeDTO {
    private String name;
    private String email;
    private String image;
    private String role;
    private String password;
    private String telephone;
}
