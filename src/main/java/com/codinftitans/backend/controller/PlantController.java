package com.codinftitans.backend.controller;

import com.codinftitans.backend.dto.PlantByUserDTO;
import com.codinftitans.backend.model.Plant;
import com.codinftitans.backend.service.PlantService;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping("/by-day")
    public List<Object[]> getPlantationsByDay() {
        return plantService.getPlantationsByDay();
    }

    @GetMapping("/by-month")
    public List<Object[]> getPlantationsByMonth() {
        return plantService.getPlantationsByMonth();
    }

    @GetMapping("/by-category")
    public List<Object[]> getPlantationsByCategory() {
        return plantService.getPlantationsByCategory();
    }

    @GetMapping("/percentage-by-category")
    public List<Object[]> getPercentagePlantationsByCategory() {
        return plantService.getPercentagePlantationsByCategory();
    }

    @GetMapping("/by-location")
    public List<Object[]> getPlantationsByLocation() {
        return plantService.getPlantationsByLocation();
    }

    @GetMapping("/most-planted-type")
    public Object[] getMostPlantedTreeType() {
        return plantService.getMostPlantedTreeType();
    }

    @GetMapping("/least-active-location")
    public Object[] getLeastActivePlantingLocation() {
        return plantService.getLeastActivePlantingLocation();
    }
    @GetMapping("/{idUser}")
    List<PlantByUserDTO> getPlantByUser(@PathVariable UUID idUser){
       return plantService.getPlantsByUser(idUser);
    }
    @PostMapping("/new")
    List<Plant> addPlant(){
        return null;
    }
}
