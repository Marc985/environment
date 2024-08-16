package com.codinftitans.backend.security;

import com.codinftitans.backend.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
