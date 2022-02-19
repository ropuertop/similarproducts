package com.inditex.similars.api;

import com.inditex.similars.api.dto.DTOSimilarProducts;
import org.springframework.http.ResponseEntity;

@FunctionalInterface
public interface IProductApi {

    /**
     * Get the similar products related to the product passed by parameter
     *
     * @param productId received from the request
     * @return the similar {@link com.inditex.similars.core.model.Product} associated to the one passed by parameter.
     */
    ResponseEntity<DTOSimilarProducts> getProductSimilar(final String productId);
}
