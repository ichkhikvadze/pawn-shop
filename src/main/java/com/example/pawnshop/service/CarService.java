package com.example.pawnshop.service;

import com.example.pawnshop.entity.Car;
import com.example.pawnshop.exception.ItemNotFoundException;
import com.example.pawnshop.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Iterable<Car> findAllCars() {
        log.info("Find all cars");
        return carRepository.findAll();
    }

    public Car createCar(final Car car) {
        log.info(String.format("Car %s", car));
        return carRepository.save(car);
    }

    public Car findCarById(final long id) {
        log.info(String.format("Find car by id = %s", id));
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty()) {
            String errorMessage = String.format("Car with id = %s not found", id);
            log.error(errorMessage);
            throw new ItemNotFoundException(errorMessage);
        }
        return carOptional.get();
    }

    public Car updateCar(final long id, final Car car) {
        log.info(String.format("Update car with id = %s to %s", id, car));
        if (!carRepository.existsById(id)) {
            String errorMessage = String.format("Car with id = %s not found", id);
            log.error(errorMessage);
            throw new ItemNotFoundException(errorMessage);
        }
        car.setId(id);
        return carRepository.save(car);
    }

    public Car deleteCarById(final long id) {
        log.info(String.format("Delete car with id = %s", id));
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty()) {
            String errorMessage = String.format("Car with id = %s not found", id);
            log.error(errorMessage);
            throw new ItemNotFoundException(errorMessage);
        }
        carRepository.deleteById(id);
        return carOptional.get();
    }
}
