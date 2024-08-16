package com.codinftitans.backend.service;

import com.codinftitans.backend.dto.CreatePlantDTO;
import com.codinftitans.backend.dto.PlantByUserDTO;
import com.codinftitans.backend.model.Location;
import com.codinftitans.backend.model.Plant;
import com.codinftitans.backend.model.TreeType;
import com.codinftitans.backend.repository.LocationRepository;
import com.codinftitans.backend.repository.PlantRepository;
import com.codinftitans.backend.repository.TreeTypeRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private TreeTypeRepository treeTypeRepository;
    @Autowired
    S3Service s3service;
    @Autowired
    LocationRepository locationRepository;

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
    public List<Plant> savePlants(List<CreatePlantDTO> plantsToSave, List<MultipartFile> images){
        List<Plant> plants=plantsToSave.stream().map(plant->{
            int index = plantsToSave.indexOf(plant);
            MultipartFile file = images.get(index);

            // Sauvegarde du fichier sur le serveur et obtention du chemin
            String fileName = null;
            try {
                fileName = s3service.uploadFile(file.getOriginalFilename().replace(" ",""),file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Mapper le DTO à l'entité Plant et associer le chemin de l'image
            Plant plantEntity = mappToPlan(plant);
            plantEntity.setImage(fileName);

            return plantEntity;
        }).toList();
       ;
       // plantRepository.sa
        return  plantRepository.saveAll(plants);
    }

    private Plant mappToPlan(CreatePlantDTO createPlantDTO){
        Optional<TreeType> treeType = treeTypeRepository.findById(createPlantDTO.getIdTreeType());
        if (treeType.isEmpty()) {
            throw new RuntimeException("TreeType not found with ID: " + createPlantDTO.getIdTreeType());
        }

        // Créer et sauvegarder l'entité Location
        Location location = new Location();
        location.setLatitude(createPlantDTO.getLocation().getLatitude());
        location.setLongitude(createPlantDTO.getLocation().getLongitude());
        location.setName(createPlantDTO.getLocation().getName());  // J'ai changé `createPlantDTO.getName()` en `createPlantDTO.getLocation().getName()`

        // Sauvegarder l'entité Location
        location = locationRepository.save(location);

        // Créer et remplir l'entité Plant
        Plant plant = new Plant();
        plant.setImage(createPlantDTO.getImage());
        plant.setDate(createPlantDTO.getDate());
        plant.setName(createPlantDTO.getName());
        plant.setLocation(location);  // Utilisation de l'objet Location persistant
        plant.setIdUser(createPlantDTO.getIdUser());
        plant.setTreeType(treeType.get());
        plant.setIdTreeType(treeType.get().getId());
        plant.setIdLocation(location.getId());


        return plant;
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
