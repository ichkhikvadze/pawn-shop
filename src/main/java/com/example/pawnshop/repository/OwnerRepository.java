package com.example.pawnshop.repository;

import com.example.pawnshop.entity.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    boolean existsByPersonalNo(String personalNo);
    Optional<Owner>findByPersonalNo(String personalNo);
}
