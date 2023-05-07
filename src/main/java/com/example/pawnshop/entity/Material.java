package com.example.pawnshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Material {

    @Id
    @Min(1)
    @EqualsAndHashCode.Include
    private long id;
    @Column(name = "address", nullable = false)
    @Length(max = 20)
    @NotBlank
    private String name;
}
