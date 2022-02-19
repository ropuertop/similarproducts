package com.inditex.similars.config;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.inditex.similars.core.service.IProductService;
import com.inditex.similars.external.IProductExternal;
import com.inditex.similars.external.components.ProductExternalImpl;
import com.inditex.similars.external.components.client.IMockRest;
import com.inditex.similars.external.components.client.impl.MockRestImpl;
import com.inditex.similars.core.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@EnableCaching
@Configuration
public class SimilarConfiguration {

    @Value("${product.hazelcast.ttl:5000}")
    private int TTL;
    @Value("${product.hazelcast.size:200}")
    private int CACHE_SIZE;

    @Bean(name = "product/external/client/mocks")
    public IMockRest mockRest()
    {
        return new MockRestImpl();
    }

    @Bean(name = "product/external")
    public IProductExternal imperativeProductRepository(@Qualifier("product/external/client/mocks") final IMockRest mockRest)
    {
        return new ProductExternalImpl(mockRest);
    }

    @Bean(name = "product/service")
    public IProductService impProductService(@Qualifier("product/external") final IProductExternal productRepository)
    {
        return new ProductServiceImpl(productRepository);
    }

    @Primary
    @Bean(name = "product/cache")
    public HazelcastInstance cacheConfig() {
        final var config = new Config()
                .addMapConfig(new MapConfig()
                        .setName("product-cache")
                        .setInMemoryFormat(InMemoryFormat.BINARY)
                        .setTimeToLiveSeconds(this.TTL)
                        .setEvictionConfig(new EvictionConfig()
                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setSize(this.CACHE_SIZE)
                        )
                );

        return Hazelcast.newHazelcastInstance(config);
    }
}
