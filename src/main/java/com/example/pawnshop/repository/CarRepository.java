package com.example.pawnshop.repository;

import com.example.pawnshop.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
