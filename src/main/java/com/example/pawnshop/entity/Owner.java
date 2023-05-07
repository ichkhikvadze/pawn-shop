package com.example.pawnshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "client_id_gen")
    @SequenceGenerator(name = "client_id_gen", sequenceName = "client_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private long id;
    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;
    @Column(name = "birth_date", nullable = false)
    @NotNull
    @Past
    private LocalDate birthDate;
    @Column(name = "personal_no", nullable = false, unique = true)
    @Pattern(regexp = "[0-9]{11}")
    private String personalNo;
    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Item> itemList;
}
