package com.inditex.similars.external.components.client.mapper;


import com.inditex.similars.core.model.Product;
import com.inditex.similars.external.components.client.dto.DTOProductDetail;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

@Log4j2
public final class MockMapper {

    /**
     * Utility class cannot be instantiated
     */
    private MockMapper()
    {
        throw new AssertionError("This class cannot be instantiated");
    }

    /**
     * This method is in charge of map and validate the {@link DTOProductDetail}
     *
     * @param nullableDTOProductDetail the received {@link DTOProductDetail}
     * @return a new {@link Product} filled with the received {@link DTOProductDetail} data
     */
    public static Optional<Product> map(final DTOProductDetail nullableDTOProductDetail)
    {
        // building the domain model
        return Optional.ofNullable(nullableDTOProductDetail).map(DTOProductDetail -> Product.builder()
                .id(Integer.valueOf(DTOProductDetail.getId()))
                .name(DTOProductDetail.getName())
                .price(BigDecimal.valueOf(DTOProductDetail.getPrice()))
                .availability(DTOProductDetail.getAvailability())
                .similarProducts(new HashSet<>(0))
                .build());
    }


}
