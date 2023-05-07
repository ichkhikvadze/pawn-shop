package com.example.pawnshop.service;

import com.example.pawnshop.entity.Technic;
import com.example.pawnshop.exception.ItemNotFoundException;
import com.example.pawnshop.repository.TechnicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TechnicService {

    private final TechnicRepository technicRepository;

    @Autowired
    public TechnicService(TechnicRepository technicRepository) {
        this.technicRepository = technicRepository;
    }

    public Iterable<Technic> findAllTechnic() {
        log.info("Find all technic");
        return technicRepository.findAll();
    }

    public Technic createTechnic(final Technic technic) {
        log.info(String.format("Create %s", technic));
        return technicRepository.save(technic);
    }

    public Technic findTechnicById(final long id) {
        log.info(String.format("Find technic by id = %s", id));
        Optional<Technic> technicOptional = technicRepository.findById(id);
        if (technicOptional.isEmpty()) {
            String errorMessage = String.format("Technic with id = %s not found", id);
            log.error(errorMessage);
            throw new ItemNotFoundException(errorMessage);
        }
        return technicOptional.get();
    }

    public Technic updateTechnic(final long id, final Technic technic) {
        log.info(String.format("Update technic with id = %s to %s", id, technic));
        if (!technicRepository.existsById(id)) {
            String errorMessage = String.format("Technic with id = %s not found", id);
            log.error(errorMessage);
            throw new ItemNotFoundException(errorMessage);
        }
        technic.setId(id);
        return technicRepository.save(technic);
    }

    public Technic deleteTechnicById(final long id) {
        log.info(String.format("Delete technic with id = %s", id));
        Optional<Technic> technicOptional = technicRepository.findById(id);
        if (technicOptional.isEmpty()) {
            String errorMessage = String.format("Technic with id = %s not found", id);
            log.error(errorMessage);
            throw new ItemNotFoundException(errorMessage);
        }
        technicRepository.deleteById(id);
        return technicOptional.get();
    }
}
