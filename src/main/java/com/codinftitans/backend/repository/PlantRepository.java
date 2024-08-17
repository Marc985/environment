package com.codinftitans.backend.repository;

import com.codinftitans.backend.model.Plant;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PlantRepository  extends JpaRepository<Plant, UUID> {
    @Query("SELECT p.date, COUNT(p) FROM Plant p GROUP BY p.date ORDER BY p.date")
    List<Object[]> countPlantationsByDay();

    @Query("SELECT FUNCTION('DATE_TRUNC', 'month', p.date), COUNT(p) FROM Plant p GROUP BY FUNCTION('DATE_TRUNC', 'month', p.date) ORDER BY FUNCTION('DATE_TRUNC', 'month', p.date)")
    List<Object[]> countPlantationsByMonth();

    @Query("SELECT p.treeType.type, COUNT(p) FROM Plant p JOIN p.treeType tt GROUP BY tt.type ORDER BY COUNT(p) DESC")
    List<Object[]> countPlantationsByCategory();

    @Query("""
           SELECT p.treeType.type, COUNT(p), 
           ROUND((COUNT(p) * 1.0 / (SELECT COUNT(pl) FROM Plant pl) * 100), 2)
           FROM Plant p
           JOIN p.treeType tt
           GROUP BY tt.type
           ORDER BY COUNT(p) DESC
           """)
    List<Object[]> percentagePlantationsByCategory();

    @Query("SELECT p.location.name, COUNT(p) FROM Plant p JOIN p.location l GROUP BY l.name ORDER BY COUNT(p) DESC")
    List<Object[]> countPlantationsByLocation();


    @Query("SELECT p.treeType.type, COUNT(p) FROM Plant p JOIN p.treeType tt GROUP BY tt.type ORDER BY COUNT(p) DESC LIMIT 1")
    Object[] mostPlantedTreeType();

    @Query("SELECT p.location.name, COUNT(p) FROM Plant p JOIN p.location l GROUP BY l.name ORDER BY COUNT(p) ASC LIMIT 1")
    Object[] leastActivePlantingLocation();
    @Query(value = "select * from plant where id_user=:id_user",nativeQuery = true)
    List<Tuple> getPlantByUser(@Param("id_user") UUID idUser);

}
