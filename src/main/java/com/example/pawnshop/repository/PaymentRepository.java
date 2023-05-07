package com.example.pawnshop.repository;

import com.example.pawnshop.entity.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
    @Query("SELECT SUM(p.amount) FROM Payment p " +
    "WHERE p.item.id = :itemId " +
    "AND p.paymentDate BETWEEN :startDate AND :endDate")
    BigDecimal findPaymentAmountInMonthForItem(long itemId, LocalDate startDate, LocalDate endDate);
}
