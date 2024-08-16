package com.codinftitans.backend.controller;

import com.codinftitans.backend.security.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
