package com.inditex.similars.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class BlockingConfigurationTest {

    @InjectMocks
    private SimilarConfiguration similarConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mockRest() {
    }

    @Test
    void imperativeProductRepository() {
    }

    @Test
    void impProductService() {
    }

    @Test
    void cacheConfig() {
    }
}