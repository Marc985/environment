package com.codinftitans.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlantByUserDTO {
    private UUID id;

    private LocalDate date; // Date of the plant entry

    private String image; // URL or path to the plant's image

    private String name; // Name of the plant species

    private UUID idLocation; // ID of the location where the plant is found

    private UUID idUser; // ID of the user who recorded the plant entry

    private UUID idTreeType;
}
