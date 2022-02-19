package com.inditex.similars.core.exception;

import com.inditex.similars.core.exception.global.ProductDomainException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

@Log4j2
public class ProductNotFoundException extends ProductDomainException {

    private static final String CODE = "PRODUCT-001";
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    /**
     * This class will be generated if the product does not exist
     *
     * @param message message associated to the exception
     */
    public ProductNotFoundException(String message) {
        super(CODE, message, HTTP_STATUS);
        log.warn(message);
    }
}
