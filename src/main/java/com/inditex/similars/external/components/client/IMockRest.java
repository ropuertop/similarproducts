package com.inditex.similars.external.components.client;

import com.inditex.similars.external.components.client.dto.DTOProductDetail;
import com.inditex.similars.core.model.Product;

import java.util.Optional;

/**
 * This interface will declare every functionality exposed by mock-service
 */
public interface IMockRest {

    String SIMILAR_PRODUCTS_URL = "http://localhost:3001/product/{productId}/similarids";
    String PRODUCT_DETAIL_URL = "http://localhost:3001/product/{productId}";

    /**
     * Retrieve the similar products associated to the product passed by parameter
     *
     * @param productId identifier associated to the product
     * @return an array with every similar product id related to the {@link Product} passed by parameter
     */
    Integer[] getSimilarProducts(final Integer productId);

    /**
     * This method is in charge of retrieving the product (passed by parameter) details.
     *
     * @param productId that we want to get its details
     * @return a new {@link DTOProductDetail}
     */
    Optional<Product> getProductDetail(final Integer productId);

}
