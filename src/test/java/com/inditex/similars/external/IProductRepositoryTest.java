package com.inditex.similars.external;

import com.inditex.similars.core.model.Product;
import com.inditex.similars.external.components.ProductExternalImpl;
import com.inditex.similars.external.components.client.dto.DTOProductDetail;
import com.inditex.similars.external.components.client.impl.MockRestImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SuppressWarnings("IgnoredJUnitTest")
@Disabled
@ExtendWith(MockitoExtension.class)
class IProductRepositoryTest {

    @InjectMocks
    private ProductExternalImpl productRepository;
    @Mock
    private MockRestImpl mockRest;

//    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_METHOD)
    class FindById
    {
        @Test
        @DisplayName("(FindById) -> is the happy path working well?")
        void findById() {
            // given
            final var id = 1;
            var product = mock(Product.class);
            var productDetailDTO = mock(DTOProductDetail.class);

            // when
            when(productRepository.findById(anyInt())).thenReturn(product);

            when(product.getId()).thenReturn(id);
            when(product.getAvailability()).thenReturn(true);
            when(product.getName()).thenReturn("ZARA SECRET PRODUCT");
            when(product.getPrice()).thenReturn(BigDecimal.ONE);
            when(product.getSimilarProducts()).thenReturn(Set.of());

            // client responses mocking
            when(mockRest.getProductDetail(any())).thenReturn(Optional.of(product));
            when(mockRest.getSimilarProducts(anyInt())).thenReturn(new Integer[]{});

            when(productDetailDTO.getId()).thenReturn(BigDecimal.valueOf(Math.random()).toString());
            when(productDetailDTO.getPrice()).thenReturn(1d);
            when(productDetailDTO.getAvailability()).thenReturn(true);
            when(productDetailDTO.getName()).thenReturn("ZARA NON-SECRET PRODUCT");

            // then
            assertThrows(RuntimeException.class, () -> productRepository.findById(1));
        }

        @Test
        void findByIdNoProduct()
        {
            // given
            final var id = BigDecimal.valueOf(Math.random()).intValue();

            // when
            when(productRepository.findById(anyInt())).thenReturn(null);

            // then
            assertDoesNotThrow(() -> productRepository.findById(id));
        }
    }
}