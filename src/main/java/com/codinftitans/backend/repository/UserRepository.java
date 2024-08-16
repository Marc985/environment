package com.codinftitans.backend.repository;

import com.codinftitans.backend.dto.MostActUser;
import com.codinftitans.backend.model.User;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "select * from \"user\" where email=:email",nativeQuery = true)
    User findByEmail(@Param("email") String email);

    @Query(value = "select * from \"user\" where role=:role",nativeQuery = true)
    List<User> findByRole(@Param("role") String role);
    @Query("SELECT u.id AS userId, u.email AS email, u.name AS name, u.telephone AS telephone, u.image AS image, COUNT(p.id) AS plantCount " +
            "FROM User u " +
            "JOIN Plant p ON u.id = p.idUser " +
            "GROUP BY u.id, u.email, u.name, u.telephone, u.image " +
            "ORDER BY COUNT(p.id) DESC LIMIT 1")
    Tuple getTheMostActifUser();
}

