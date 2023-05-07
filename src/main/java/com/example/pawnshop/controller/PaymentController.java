package com.example.pawnshop.controller;

import com.example.pawnshop.entity.Payment;
import com.example.pawnshop.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Get all payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all payment"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public Iterable<Payment> findAllPayment() {
        return paymentService.findAllPayment();
    }

    @Operation(summary = "Create payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment created"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "409", description = "Payment already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Payment createPayment(@Parameter(description = "Payment") @RequestBody @Valid Payment payment) {
        return paymentService.createPayment(payment);
    }

    @Operation(summary = "Find payment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment found"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    @GetMapping("/{id}")
    public Payment findPaymentById(@Parameter(description = "Payment id") @PathVariable(name = "id") final long id) {
        return paymentService.findPaymentById(id);
    }

    @Operation(summary = "Update payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment updated"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "404", description = "Payment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public Payment updatePayment(
            @Parameter(description = "Payment id") @RequestParam final long id,
            @Parameter(description = "Payment") @RequestBody @Valid final Payment payment) {
        return paymentService.updatePayment(id, payment);
    }

    @Operation(summary = "Delete payment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Payment deleted"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping
    public Payment deletePaymentById(@Parameter(description = "Payment id") @RequestParam final long id) {
        return paymentService.deletePaymentById(id);
    }

    @Operation(summary = "Delete payment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Total payment in moth found"),
            @ApiResponse(responseCode = "400", description = "Invalid format")
    })
    @GetMapping("/monthly/{itemId}")
    public BigDecimal findPaymentAmountInMonthForItem(
            @Parameter(description = "Item id") @PathVariable(name = "itemId") long itemId,
            @Parameter(description = "Start of the month") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startOfTheMonth,
            @Parameter(description = "End of the month") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endOfTheMonth
    ) {
        return paymentService.findPaymentAmountInMonthForItem(itemId, startOfTheMonth, endOfTheMonth);
    }
}
