package com.example.pawnshop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Jewelry extends Item {

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "jewelry_material",
            joinColumns = @JoinColumn(name = "jewelry_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "material_id", referencedColumnName = "id")
    )
    private List<Material> materialList;
}
