package com.inditex.similars.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

/**
 * Root aggregate domain model
 * @author ropuertop
 */
@Log4j2
@Getter
@Builder
public class Product implements ProductFunctionality, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identifier associated to the product
     */
    @Min(value = 1, message = "INTERNAL ERROR SERVER")
    private final Integer id;

    /**
     * Name associated to the product
     */
    @Setter
    @NotBlank(message = "The product name must has a valid name (blank names are disallowed)")
    @Length(min = 1)
    private String name;

    /**
     * Cost associated to the product
     */
    @Setter
    @DecimalMin(value = "1.0", message = "The product price must be greater than 1")
    private BigDecimal price;

    /**
     * See if the product is available to buy
     */
    @Setter
    private Boolean availability;

    @NotNull
    private Set<Product> similarProducts;

    @Override
    public void updateSimilarProducts(final Collection<Product> similarProducts) {
        log.info("Updating the similar products [{}]", similarProducts);
        this.similarProducts = Set.copyOf(similarProducts);
    }

    @Override
    public Boolean isValid() {
        return VALIDATOR.validate(this).isEmpty();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                '}';
    }
}
