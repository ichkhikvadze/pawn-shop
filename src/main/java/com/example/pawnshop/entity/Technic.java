package com.example.pawnshop.entity;

import com.example.pawnshop.entity.enums.TechnicType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Technic extends Item {

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private TechnicType type;
    @Column(name = "manufacturer", nullable = false)
    @NotBlank
    private String manufacturer;
    @Column(name = "flaw_description")
    private String flawDescription;
}
