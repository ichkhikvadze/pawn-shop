package com.example.pawnshop.service;

import com.example.pawnshop.entity.Jewelry;
import com.example.pawnshop.exception.ItemNotFoundException;
import com.example.pawnshop.repository.JewelryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class JewelryService {

    private final JewelryRepository jewelryRepository;

    @Autowired
    public JewelryService(JewelryRepository jewelryRepository) {
        this.jewelryRepository = jewelryRepository;
    }

    public Iterable<Jewelry> findAllJewelry() {
        log.info("Find all jewelries");
        return jewelryRepository.findAll();
    }

    public Jewelry createJewelry(final Jewelry jewelry) {
        log.info(String.format("Create %s", jewelry));
        return jewelryRepository.save(jewelry);
    }

    public Jewelry findJewelryById(final long id) {
        log.info(String.format("Find jewelry by id = %s", id));
        Optional<Jewelry> jewelryOptional = jewelryRepository.findById(id);
        if (jewelryOptional.isEmpty()) {
            String errorMessage = String.format("Jewelry with id = %s not found", id);
            log.error(errorMessage);
            throw new ItemNotFoundException(errorMessage);
        }
        return jewelryOptional.get();
    }

    public Jewelry updateJewelry(final long id, final Jewelry jewelry) {
        log.info(String.format("Update jewelry with id = %s to %s", id, jewelry));
        if (!jewelryRepository.existsById(id)) {
            String errorMessage = String.format("Jewelry with id = %s not found", id);
            log.error(errorMessage);
            throw new ItemNotFoundException(errorMessage);
        }
        jewelry.setId(id);
        return jewelryRepository.save(jewelry);
    }

    public Jewelry deleteJewelryById(final long id) {
        log.info(String.format("Delete jewelry with id = %s", id));
        Optional<Jewelry> jewelryOptional = jewelryRepository.findById(id);
        if (jewelryOptional.isEmpty()) {
            String errorMessage = String.format("Jewelry with id = %s not found", id);
            log.error(errorMessage);
            throw new ItemNotFoundException(errorMessage);
        }
        jewelryRepository.deleteById(id);
        return jewelryOptional.get();
    }
}
