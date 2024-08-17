package com.codinftitans.backend.dto;

import com.codinftitans.backend.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePlantDTO {
    private String image;
    private LocalDate date;
    private String name;
    private UUID idUser;
    private Location location;
    private UUID idTreeType;
}
