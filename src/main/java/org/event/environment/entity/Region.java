package org.event.environment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_location", referencedColumnName = "id",insertable = false,updatable = false)
    private Location location;
    @Column(name = "id_location")
    private UUID idLocation;
    @OneToMany(mappedBy = "region",fetch = FetchType.EAGER)
    private Set<Tree> trees;
}
