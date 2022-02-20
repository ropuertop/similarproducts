package com.inditex.similars.core.service;

import com.inditex.similars.core.model.Product;

import java.util.Collection;

/**
 * This interface will be connected with the entry point of the service.
 *
 * @author ropuertop
 */
@FunctionalInterface
public interface IProductService{

    /**
     * Retrieve the collection of similar {@link Product}
     * @param id associated to the {@link Product} related with the similar ones
     * @return the collection related to the {@link Product} passed by parameter
     */
    Collection<Product> getSimilarProducts(final Integer id);
}
