package com.codinftitans.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetTreeType {
    private UUID id;
    private String description;
    private String type;
}
