package com.example.pawnshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Branch {

    @Id
    @Min(1)
    @EqualsAndHashCode.Include
    private long id;
    @Column(name = "address", nullable = false)
    @Length(max = 50)
    @NotBlank
    private String address;
    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private List<Item> itemList;
}
