package com.inditex.similars.core.exception.global;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ProductDomainException extends RuntimeException{

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    protected ProductDomainException(final String code,
                                     final String message,
                                     final HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
