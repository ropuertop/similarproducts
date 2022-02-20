package com.inditex.similars.core.exception.global;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ProductDomainException extends RuntimeException{

    /**
     * Exception code
     * @implNote it must be defined in class. It's a bad practice to pass it by constructor.
     */
    private final String code;

    /**
     * Exception HTTP status
     * @implNote it must be defined in class. It's a bad practice to pass it by constructor.
     */
    private final HttpStatus httpStatus;

    /**
     * Exception message
     * @implNote you must pass a message defining what happened in the exception constructor
     * @see com.inditex.similars.core.exception.ProductNotFoundException
     */
    private final String message;

    /**
     * Global constructor.
     * @implNote You must invoke it on your custom exceptions.
     *
     * @param code          exception code
     * @param message       exception message
     * @param httpStatus    exception http status
     */
    protected ProductDomainException(final String code,
                                     final String message,
                                     final HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
