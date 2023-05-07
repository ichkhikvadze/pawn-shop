package com.example.pawnshop.service;

import com.example.pawnshop.entity.Material;
import com.example.pawnshop.exception.MaterialAlreadyExistsException;
import com.example.pawnshop.exception.MaterialNotFoundException;
import com.example.pawnshop.repository.MaterialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Iterable<Material> findAllMaterials() {
        log.info("Find all materials");
        return materialRepository.findAll();
    }

    public Material createMaterial(final Material material) {
        log.info(String.format("Create %s", material));
        Optional<Material> materialOptional = materialRepository.findById(material.getId());
        if (materialOptional.isPresent()) {
            String errorMessage = String.format("Material with id = %s already exists", material.getId());
            log.error(errorMessage);
            throw new MaterialAlreadyExistsException(errorMessage);
        }
        return materialRepository.save(material);
    }

    public Material findMaterialById(final long id) {
        log.info(String.format("Find material by id = %s", id));
        Optional<Material> materialOptional = materialRepository.findById(id);
        if (materialOptional.isEmpty()) {
            String errorMessage = String.format("Material with id = %s not found", id);
            log.error(errorMessage);
            throw new MaterialNotFoundException(errorMessage);
        }
        return materialOptional.get();
    }

    public Material updateMaterial(final long id, final Material material) {
        log.info(String.format("Update material with id = %s to %s", id, material));
        if (!materialRepository.existsById(id)) {
            String errorMessage = String.format("Material with id = %s not found", id);
            log.error(errorMessage);
            throw new MaterialNotFoundException(errorMessage);
        }
        material.setId(id);
        return materialRepository.save(material);
    }

    public Material deleteMaterialById(final long id) {
        log.info(String.format("Delete material with id = %s", id));
        Optional<Material> materialOptional = materialRepository.findById(id);
        if (materialOptional.isEmpty()) {
            String errorMessage = String.format("Material with id = %s not found", id);
            log.error(errorMessage);
            throw new MaterialNotFoundException(errorMessage);
        }
        materialRepository.deleteById(id);
        return materialOptional.get();
    }
}
