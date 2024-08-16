package org.event.environment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private String image;
    @ManyToOne
    @JoinColumn(name = "id_region", referencedColumnName = "id",insertable = false,updatable = false)
    private Region region;
    @Column(name="id_region")
    private UUID idRegion;
}
