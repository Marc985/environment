package com.codinftitans.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MostActUser {
    private UUID userId;
    private String email;
    private String name;
    private String telephone;
    private String image;
    private long plantCount;
}
