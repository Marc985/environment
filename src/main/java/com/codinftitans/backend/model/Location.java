package com.codinftitans.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String longitude;
    private String latitude;
    @ManyToOne
    @JoinColumn(name = "id_location", referencedColumnName = "id",insertable = false,updatable = false)
    private Location location;
    @Column(name = "id_location")
    private UUID idLocation;
    @OneToMany(mappedBy = "location",fetch = FetchType.EAGER)
    private Set<Plant> plants;
}
