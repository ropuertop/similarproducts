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
     * Retrieve a collection of
     * @param id identifier associate to the root product
     * @return a {@link java.util.Collection} or a {@link reactor.core.publisher.Flux} (it depends on the active profile) of each similar product
     * @implNote THIS IS NOT A GOOD PRACTICE!
     * I'm defining the generic type because {@link reactor.core.publisher.Flux} and {@link java.util.Collection} cannot be gathered.
     * This is an example of extreme hexagonal architecture. Normally is rare to reuse the same interface when you want to migrate
     * to Reactive paradigm.
     */
    Collection<Product> getSimilarProducts(final Integer id);
}
