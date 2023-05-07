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
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_gen")
    @SequenceGenerator(name = "payment_id_gen", sequenceName = "payment_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "payment_date", nullable = false)
    @NotNull
    @Past
    private LocalDate paymentDate;
    @Column(name = "amount", nullable = false)
    @NotNull
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
