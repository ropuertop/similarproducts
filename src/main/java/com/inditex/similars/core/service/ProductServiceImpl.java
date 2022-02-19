package com.inditex.similars.core.service;

import com.inditex.similars.core.model.Product;
import com.inditex.similars.external.IProductExternal;
import lombok.extern.log4j.Log4j2;

import java.util.Collection;

import com.inditex.similars.external.components.ProductExternalImpl;

@Log4j2
public final class ProductServiceImpl implements IProductService
{
    /**
     * The third party connector
     * @see ProductExternalImpl
     */
    private final IProductExternal productRepository;

    public ProductServiceImpl(final IProductExternal productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Collection<Product> getSimilarProducts(final Integer id)
    {
        log.debug("(IProductService) -> (getSimilarProducts): Getting the similar products associated to the [{}] product", id);
        return productRepository.findById(id).getSimilarProducts();
    }
}
