package com.example.pawnshop.service;

import com.example.pawnshop.entity.Item;
import com.example.pawnshop.entity.Payment;
import com.example.pawnshop.exception.PaymentNotFoundException;
import com.example.pawnshop.repository.ItemRepository;
import com.example.pawnshop.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, ItemRepository itemRepository) {
        this.paymentRepository = paymentRepository;
        this.itemRepository = itemRepository;
    }

    public Iterable<Payment> findAllPayment() {
        log.info("Find all payments");
        return paymentRepository.findAll();
    }

    public Payment createPayment(final Payment payment) {
        log.info(String.format("Create %s", payment));
        return paymentRepository.save(payment);
    }

    public Payment findPaymentById(final long id) {
        log.info(String.format("Find payment by id = %s", id));
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isEmpty()) {
            String errorMessage = String.format("Payment with id = %s not found", id);
            log.error(errorMessage);
            throw new PaymentNotFoundException(errorMessage);
        }
        return paymentOptional.get();
    }

    public Payment updatePayment(final long id, final Payment payment) {
        log.info(String.format("Update payment with id = %s to %s", id, payment));
        if (!paymentRepository.existsById(id)) {
            String errorMessage = String.format("Payment with id = %s not found", id);
            log.error(errorMessage);
            throw new PaymentNotFoundException(errorMessage);
        }
        payment.setId(id);
        return paymentRepository.save(payment);
    }

    public Payment deletePaymentById(final long id) {
        log.info(String.format("Delete payment with id = %s", id));
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isEmpty()) {
            String errorMessage = String.format("Payment with id = %s not found", id);
            log.error(errorMessage);
            throw new PaymentNotFoundException(errorMessage);
        }
        paymentRepository.deleteById(id);
        return paymentOptional.get();
    }

    public BigDecimal findPaymentAmountInMonthForItem(long itemId, LocalDate startOfTheMonth, LocalDate endOfTheMonth) {
        log.info(String.format("Find amount of payment for item id = %s, between %s and %s", itemId, startOfTheMonth, endOfTheMonth));
        return paymentRepository.findPaymentAmountInMonthForItem(itemId, startOfTheMonth, endOfTheMonth);
    }

    @Scheduled(cron = "1 0 0 ? * ?")
    public void checkMonthlyPayment() {
        Iterable<Item> itemList = itemRepository.findAll();
        itemList.forEach(item -> {
            if (paymentForItemNeedsCheck(item)) {
                long itemId = item.getId();
                LocalDate startOfTheMonth = LocalDate.now().minusMonths(1);
                LocalDate endOfTheMonth = LocalDate.now();
                BigDecimal paidInCurrentMonth = findPaymentAmountInMonthForItem(itemId, startOfTheMonth, endOfTheMonth);
                if (paidInCurrentMonth.compareTo(item.getMonthFee()) < 0) {
                    item.setConfiscated(true);
                    itemRepository.save(item);
                }
            }
        });
    }

    private boolean paymentForItemNeedsCheck(Item item) {
        return item.getPawnDate().getDayOfMonth() == LocalDate.now().getDayOfMonth() && !item.isConfiscated();
    }
}
