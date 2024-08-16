package com.codinftitans.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String image;
    @ManyToOne
    @JoinColumn(name = "id_location",referencedColumnName = "id",insertable = false,updatable = false)
    private Location location;
    @Column(name = "id_location")
    private UUID idLocation;
    LocalDate date;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id",insertable = false,updatable = false)
    private User user;
    @Column(name = "id_user")
    private UUID idUser;
    @ManyToOne
    @JoinColumn(name = "id_tree_type", referencedColumnName = "id",insertable = false,updatable = false)
    private TreeType treeType;
    @Column(name = "id_tree_type")
    private UUID idTreeType;

}
