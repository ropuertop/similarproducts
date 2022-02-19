package com.inditex.similars.external.components;

import com.inditex.similars.core.exception.ProductNotFoundException;
import com.inditex.similars.core.exception.global.ProductDomainException;
import com.inditex.similars.core.model.Product;
import com.inditex.similars.external.IProductExternal;
import com.inditex.similars.external.components.client.IMockRest;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@CacheConfig(cacheNames = "product-cache")
public class ProductExternalImpl implements IProductExternal {

    private final IMockRest mockRest;

    public ProductExternalImpl(final IMockRest mockRest) {
        this.mockRest = mockRest;
    }

    @Override
    @Cacheable(key = "#id")
    public Product findById(final Integer id) throws ProductDomainException {

        log.info("(ImperativeProductExternalImpl) -> (findById): finding domain Product associated to the identifier [{}]", id);

        // getting the product associated to the product id passed
        final var product = this.mockRest.getProductDetail(id).orElseThrow(() -> {
            final var message = String.format("There is no product details associated to [%d]", id);
            return new ProductNotFoundException(message);
        });

        // checking if the product is valid
        if(Boolean.FALSE.equals(product.isValid())) {
            log.warn("(ProductExternalImpl) -> (findById): Product [{}] is not valid", id);
            throw new ProductNotFoundException(String.format("There was a problem with the stream [%d]", id));
        }

        // getting the similar related products (we filter only for the valid ones)
        final var newSimilarProducts = Arrays.stream(mockRest.getSimilarProducts(id))
                .parallel()
                .map(mockRest::getProductDetail)
                .flatMap(Optional::stream)
                .filter(Product::isValid)
                .collect(Collectors.toSet());

        // updating the current product
        product.updateSimilarProducts(newSimilarProducts);

        // returning and caching it
        return product;
    }
}
