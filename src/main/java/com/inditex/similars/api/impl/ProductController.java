package com.inditex.similars.api.impl;

import com.inditex.similars.api.IProductApi;
import com.inditex.similars.core.service.IProductService;
import com.inditex.similars.api.dto.DTOSimilarProducts;
import com.inditex.similars.api.mapper.ProductControllerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController implements IProductApi {

    private final IProductService productService;

    @Autowired
    public ProductController(@Qualifier(value = "product/service") final IProductService productService) {
        this.productService = productService;
    }

    @Override
    @GetMapping("/{productId}/similar")
    public ResponseEntity<DTOSimilarProducts> getProductSimilar(@PathVariable(name = "productId") String productId)
    {
        // getting the similar products
        final var similarProducts = this.productService.getSimilarProducts(Integer.valueOf(productId))
                .stream()
                .map(ProductControllerMapper::map)
                .collect(Collectors.toList());

        // building the body
        final var body = DTOSimilarProducts.builder()
                .similarProducts(similarProducts)
                .build();

        // sending the body
        return ResponseEntity.ok(body);
    }
}
