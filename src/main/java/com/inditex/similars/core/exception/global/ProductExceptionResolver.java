package com.inditex.similars.core.exception.global;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * This class is in charge of resolve every domain exception
 * @author ropuertop
 */
@ControllerAdvice(basePackages = {"com.inditex.similars"})
public class ProductExceptionResolver {

    @ExceptionHandler(value = ProductDomainException.class)
    ResponseEntity<Map<String, String>> wrapGeneralException(final ProductDomainException exception)
    {
        // building the error body
        final var body = Map.of(
            "timestamp", LocalDateTime.now().toString(),
                "code", exception.getCode(),
                "message", exception.getMessage()
        );

        // building the ResponseEntity
        return ResponseEntity
                .status(exception.getHttpStatus().value())
                .body(body);
    }
}
