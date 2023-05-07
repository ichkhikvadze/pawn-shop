package com.example.pawnshop.entity;

import com.example.pawnshop.entity.enums.DistanceUnit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Car extends Item {

    @Column(name = "model", nullable = false)
    @NotBlank
    private String model;
    @Column(name = "release_date", nullable = false)
    @NotNull
    @Past
    private LocalDate releaseDate;
    @Column(name = "odometer_value", nullable = false)
    @Min(0)
    private int odometerValue;
    @Column(name = "odometer_unit", nullable = false)
    @Enumerated(EnumType.STRING)
    private DistanceUnit odometerUnit;
}
