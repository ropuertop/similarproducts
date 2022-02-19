package com.inditex.similars.external;

import com.inditex.similars.core.exception.global.ProductDomainException;
import com.inditex.similars.core.model.Product;

@FunctionalInterface
public interface IProductExternal {

    /**
     * Retrieve the domain {@link Product} from external services (db, service, broker, etc.)
     * @param id that we want to search our product from
     * @return the associated {@link Product}
     * @throws ProductDomainException if the product is not valid
     */
    Product findById(final Integer id) throws ProductDomainException;

}
