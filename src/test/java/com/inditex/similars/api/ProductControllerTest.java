package com.inditex.similars.api;

import com.inditex.similars.api.impl.ProductController;
import com.inditex.similars.core.model.Product;
import com.inditex.similars.core.service.IProductService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;
    @Mock
    private IProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("(ProductControllerTest) -> (getProductSimilar): is the happy path working?")
    void getProductSimilar() {

        // given
        final var product = mock(Product.class);
        final var similarProduct = mock(Product.class);

        // when
        when(productService.getSimilarProducts(anyInt())).thenReturn(List.of(product));
        when(product.getId()).thenReturn(1);
        when(product.getAvailability()).thenReturn(true);
        when(product.getName()).thenReturn("ZARA SECRET PRODUCT");
        when(product.getPrice()).thenReturn(BigDecimal.TEN);
        when(similarProduct.getId()).thenReturn(2);
        when(similarProduct.getAvailability()).thenReturn(false);
        when(similarProduct.getName()).thenReturn("SIMILAR ZARA SECRET PRODUCT");
        when(similarProduct.getPrice()).thenReturn(BigDecimal.TEN);
        when(product.getSimilarProducts()).thenReturn(Set.of(similarProduct));

        // then
        final var response = assertDoesNotThrow(() -> productController.getProductSimilar("1"));
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getSimilarProducts().stream().allMatch(productDetailDTO -> productDetailDTO.getId().equals("1")));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}