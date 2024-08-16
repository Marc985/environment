package com.codinftitans.backend.controller;

import com.codinftitans.backend.dto.GetTreeType;
import com.codinftitans.backend.service.TreeTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TreeTypeController {
    @Autowired
    TreeTypeService treeTypeService;
    @GetMapping("/tree/types")
    public List<GetTreeType> treeTypes(){
        return treeTypeService.treeTypes();
    }
}
