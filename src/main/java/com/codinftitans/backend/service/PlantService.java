package com.codinftitans.backend.service;

import com.codinftitans.backend.dto.PlantByUserDTO;
import com.codinftitans.backend.model.Plant;
import com.codinftitans.backend.repository.PlantRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    public List<Object[]> getPlantationsByDay() {
        return plantRepository.countPlantationsByDay();
    }

    public List<Object[]> getPlantationsByMonth() {
        return plantRepository.countPlantationsByMonth();
    }

    public List<Object[]> getPlantationsByCategory() {
        return plantRepository.countPlantationsByCategory();
    }

    public List<Object[]> getPercentagePlantationsByCategory() {
        return plantRepository.percentagePlantationsByCategory();
    }

    public List<Object[]> getPlantationsByLocation() {
        return plantRepository.countPlantationsByLocation();
    }

    public Object[] getMostPlantedTreeType() {
        return plantRepository.mostPlantedTreeType();
    }

    public Object[] getLeastActivePlantingLocation() {
        return plantRepository.leastActivePlantingLocation();
    }
    public List<PlantByUserDTO> getPlantsByUser(UUID idUser){

       List<Tuple> plants= plantRepository.getPlantByUser(idUser);
       List<PlantByUserDTO> plantsByUser=plants.stream().map(this::mapPlantTuple).toList();
       return plantsByUser;
    }
    private PlantByUserDTO mapPlantTuple(Tuple tuple){
        return  new PlantByUserDTO(
                tuple.get("id", UUID.class),
                tuple.get("date", Date.class).toLocalDate(),
                tuple.get("image", String.class),
                tuple.get("name", String.class),
                tuple.get("id_location", UUID.class),
                tuple.get("id_user", UUID.class),
                tuple.get("id_tree_type",UUID.class)
        );

    }
}
