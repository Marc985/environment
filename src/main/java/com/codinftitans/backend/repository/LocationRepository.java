package com.codinftitans.backend.repository;

import com.codinftitans.backend.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
}
