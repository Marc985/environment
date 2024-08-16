package com.codinftitans.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetEmployeeDTO {
    private UUID id;
    private String name;
    private String email;
    private String image;
    private String role;
    private String telephone;
}
