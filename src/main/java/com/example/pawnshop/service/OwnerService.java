package com.example.pawnshop.service;

import com.example.pawnshop.entity.Owner;
import com.example.pawnshop.exception.OwnerNotFoundException;
import com.example.pawnshop.exception.PersonalNoAlreadyExistsException;
import com.example.pawnshop.repository.OwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Iterable<Owner> findAllOwners() {
        log.info("Find all owners");
        return ownerRepository.findAll();
    }

    public Owner createOwner(final Owner owner) {
        log.info(String.format("Create %s", owner));
        if (ownerRepository.existsByPersonalNo(owner.getPersonalNo())) {
            String errorMessage = String.format("Owner with personal no = %s already exists", owner.getPersonalNo());
            log.error(errorMessage);
            throw new PersonalNoAlreadyExistsException(errorMessage);
        }
        return ownerRepository.save(owner);
    }

    public Owner findOwnerById(final long id) {
        log.info(String.format("Find owner by id = %s", id));
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty()) {
            String errorMessage = String.format("Owner with id = %s not found", id);
            log.error(errorMessage);
            throw new OwnerNotFoundException(errorMessage);
        }
        return ownerOptional.get();
    }

    public Owner updateOwner(final long id, final Owner owner) {
        log.info(String.format("Update owner with id = %s to %s", id, owner));
        if (!ownerRepository.existsById(id)) {
            String errorMessage = String.format("Owner with id = %s not found", id);
            log.error(errorMessage);
            throw new OwnerNotFoundException(errorMessage);
        }
        Optional<Owner> ownerOptional = ownerRepository.findByPersonalNo(owner.getPersonalNo());
        if (ownerOptional.isPresent() && ownerOptional.get().getId() != id) {
            String errorMessage = String.format("Owner with personal no = %s already exists", owner.getPersonalNo());
            log.error(errorMessage);
            throw new PersonalNoAlreadyExistsException(errorMessage);
        }
        owner.setId(id);
        return ownerRepository.save(owner);
    }

    public Owner deleteOwnerById(final long id) {
        log.info(String.format("Delete owner with id = %s", id));
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty()) {
            String errorMessage = String.format("Owner with id = %s not found", id);
            log.error(errorMessage);
            throw new OwnerNotFoundException(errorMessage);
        }
        ownerRepository.deleteById(id);
        return ownerOptional.get();
    }
}
