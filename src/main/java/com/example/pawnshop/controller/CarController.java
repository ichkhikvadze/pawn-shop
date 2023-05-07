package com.example.pawnshop.controller;

import com.example.pawnshop.entity.Car;
import com.example.pawnshop.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Get all cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all cars"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public Iterable<Car> findAllCars() {
        return carService.findAllCars();
    }

    @Operation(summary = "Create car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car created"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "409", description = "Car already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Car createCar(@Parameter(description = "Car") @RequestBody @Valid Car car) {
        return carService.createCar(car);
    }

    @Operation(summary = "Find car by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car found"),
            @ApiResponse(responseCode = "404", description = "Car not found")
    })
    @GetMapping("/{id}")
    public Car findCarById(@Parameter(description = "Car id") @PathVariable(name = "id") final long id) {
        return carService.findCarById(id);
    }

    @Operation(summary = "Update car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car updated"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public Car updateCar(
            @Parameter(description = "Car id") @RequestParam final long id,
            @Parameter(description = "Car") @RequestBody @Valid final Car car) {
        return carService.updateCar(id, car);
    }

    @Operation(summary = "Delete car by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Car deleted"),
            @ApiResponse(responseCode = "404", description = "Car not found")
    })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping
    public Car deleteCarById(@Parameter(description = "Car id") @RequestParam final long id) {
        return carService.deleteCarById(id);
    }
}
