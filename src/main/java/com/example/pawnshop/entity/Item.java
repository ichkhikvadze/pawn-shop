package com.example.pawnshop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_gen")
    @SequenceGenerator(name = "item_id_gen", sequenceName = "item_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private long id;
    @Column(name = "pawn_date", nullable = false)
    @NotNull
    @Past
    private LocalDate pawnDate;
    @Column(name = "price", nullable = false)
    @NotNull
    private BigDecimal price;
    @Column(name = "month_fee", nullable = false)
    @NotNull
    private BigDecimal monthFee;
    @Column(name = "take_out_date")
    private LocalDate takeOutDate;
    @Column(name = "is_in_pawnshop", nullable = false)
    private boolean isInPawnshop;
    @Column(name = "is_confiscated", nullable = false)
    private boolean isConfiscated;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @NotNull
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    @NotNull
    private Branch branch;
}
