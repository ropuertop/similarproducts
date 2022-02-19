package com.inditex.similars.api.mapper;

import com.inditex.similars.core.model.Product;
import com.inditex.similars.api.dto.DTOProductDetail;
import lombok.extern.log4j.Log4j2;

/**
 * This class is in charge of mapping the domain model into the entrypoint dto
 *
 * @author ropuertop
 */
@Log4j2
public final class ProductControllerMapper {

    private ProductControllerMapper()
    {
        throw new AssertionError("Utility class cannot be instantiated");
    }

    /**
     * This method is in charge of convert the domain model into the entrypoint dto
     * @param product domain model {@link Product}
     * @return a new {@link DTOProductDetail} filled with the {@link Product} data
     */
    public static DTOProductDetail map(final Product product)
    {
        log.debug("(ProductControllerMapper) -> (map): mapping the product [{}] into ProductDetailDTO", product.getId());
        return DTOProductDetail.builder()
                .id(product.getId().toString())
                .name(product.getName())
                .price(product.getPrice().doubleValue())
                .availability(product.getAvailability())
                .build();
    }
}
