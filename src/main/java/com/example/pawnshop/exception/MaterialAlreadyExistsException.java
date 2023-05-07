package com.example.pawnshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Material already exists")
public class MaterialAlreadyExistsException extends RuntimeException {
    public MaterialAlreadyExistsException(String message) {
        super(message);
    }
}
