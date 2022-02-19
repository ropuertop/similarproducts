package com.inditex.similars.core.exception;

import com.inditex.similars.core.exception.global.ProductDomainException;
import org.springframework.http.HttpStatus;

public class UnexpectedDomainException extends ProductDomainException {

    private static final String CODE = "PRODUCT-000";
    private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * This exception is thrown on non-controlled exceptions
     *
     * @param exception unexpected exception
     */
    public UnexpectedDomainException(final Exception exception) {
        super(CODE, exception.getMessage(), HTTP_STATUS);
    }

    /**
     * This exception is thrown on non-controlled exceptions
     *
     * @param exceptionMessage unexpected exception message
     */
    public UnexpectedDomainException(final String exceptionMessage) {
        super(CODE, exceptionMessage, HTTP_STATUS);
    }
}
