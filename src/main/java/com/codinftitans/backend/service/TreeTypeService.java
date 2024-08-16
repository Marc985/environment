package com.codinftitans.backend.service;

import com.codinftitans.backend.dto.GetTreeType;
import com.codinftitans.backend.model.TreeType;
import com.codinftitans.backend.repository.TreeTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeTypeService {
    @Autowired
    TreeTypeRepository treeTypeRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<GetTreeType> treeTypes(){
     List<TreeType> treetypes=treeTypeRepository.findAll();
      List<GetTreeType> getTreetypes=treetypes.stream().map(tree->modelMapper.map(tree, GetTreeType.class)).toList();
      return getTreetypes;
    }
}
