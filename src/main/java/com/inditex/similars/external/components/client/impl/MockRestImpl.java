package com.inditex.similars.external.components.client.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.similars.core.exception.ProductNotFoundException;
import com.inditex.similars.core.exception.UnexpectedDomainException;
import com.inditex.similars.core.model.Product;
import com.inditex.similars.external.components.client.IMockRest;
import com.inditex.similars.external.components.client.dto.DTOProductDetail;
import com.inditex.similars.external.components.client.mapper.MockMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;

@Log4j2
public class MockRestImpl implements IMockRest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.of(5, ChronoUnit.SECONDS))
            .build();

    @Override
    public Integer[] getSimilarProducts(final Integer productId) {
        try
        {
            // creating the request
            final var request = HttpRequest
                    .newBuilder(new URI(SIMILAR_PRODUCTS_URL.replace("{productId}", productId.toString())))
                    .GET()
                    .header("Content-Type", "application/json")
                    .header( "Accept", "application/json" )
                    .version(HttpClient.Version.HTTP_1_1)
                    .timeout(Duration.of(5, ChronoUnit.SECONDS))
                    .build();

            // sending the request
            final var response = this.client.send(request, HttpResponse.BodyHandlers.ofString());

            // creating the dto
            if(response.statusCode() == HttpStatus.OK.value())
            {
                final var optionalBody = Optional.ofNullable(this.client.send(request, HttpResponse.BodyHandlers.ofString()).body());

                return optionalBody.isPresent()
                        ? this.objectMapper.readValue(optionalBody.get(), Integer[].class)
                        : new Integer[0];
            }
            else
            {
                log.error("(MockRestImpl) -> (getSimilarProducts): unexpected error");

                // we could return the default behavior, we only need to define what we want to do on yellow paths
                throw new UnexpectedDomainException(response.body());
            }
        }
        catch (HttpTimeoutException e)
        {
            log.error("(MockRestImpl) -> (getSimilarProducts): Timeout while trying to connect with mock service for product [{}]", productId, e);

            // TODO: in the future create sometype of TODOTASK (Jira i.e) for Service Support

            // we are returning the default behavior, but we could throw an UnexpectedException or similar.
            // we only need to define what we want to do on yellow paths
            return new Integer[0];
        }
        catch (InterruptedException e)
        {
            log.fatal("(MockRestImpl) -> (getSimilarProducts): Current thread was interrupted: ", e);
            Thread.currentThread().interrupt();
            throw new UnexpectedDomainException(e);
        }
        catch (IOException | URISyntaxException exception)
        {
            log.error("(MockRestImpl) -> (getSimilarProducts): There was an exception trying to connect with mock service", exception);

            // TODO: in the future create sometype of TODOTASK (Jira i.e) for Service Support

            // we are returning the default behavior, but we could throw an UnexpectedException or similar.
            // we only need to define what we want to do on yellow paths
            return new Integer[0];
        }
    }

    @Override
    public Optional<Product> getProductDetail(Integer productId) {
        try
        {
            // creating the request
            final var request = HttpRequest
                    .newBuilder(new URI(PRODUCT_DETAIL_URL.replace("{productId}", productId.toString())))
                    .GET()
                    .header("Content-Type", "application/json")
                    .header( "Accept", "application/json" )
                    .version(HttpClient.Version.HTTP_1_1)
                    .timeout(Duration.of(5, ChronoUnit.SECONDS))
                    .build();

            // sending the request
            final var response = this.client.send(request, HttpResponse.BodyHandlers.ofString());

            // creating the dto
            if(response.statusCode() == HttpStatus.OK.value())
            {
                final var body = Optional.ofNullable(this.client.send(request, HttpResponse.BodyHandlers.ofString()).body())
                        .orElseThrow(() -> new ProductNotFoundException("Product not received from Mocks service"));

                // binding JSON properties to POJO
                final var productDTO = this.objectMapper.readValue(body, DTOProductDetail.class);
                return MockMapper.map(productDTO);
            }
            else
            {
                if(response.statusCode() == HttpStatus.NOT_FOUND.value())
                {
                    log.warn("(MockRestImpl) -> (getProductDetail): No product associated to the id [{}]", productId);
                    return Optional.empty();
                }
                else
                {
                    log.error("(MockRestImpl) -> (getProductDetail): unexpected error [{}]", response);

                    // we could return the default behavior, we only need to define what we want to do on yellow paths
                    throw new UnexpectedDomainException("There was an unexpected error");
                }
            }

        }
        catch (HttpTimeoutException | RejectedExecutionException e)
        {
            log.error("(MockRestImpl) -> (getProductDetail): There was some problems with mock client [{}]", productId);

            // TODO: in the future create sometype of TODOTASK (Jira i.e) for Service Support

            // we are returning the default behavior, but we could throw an UnexpectedException or similar.
            // we only need to define what we want to do on yellow paths
            return Optional.empty();
        }
        catch (InterruptedException e)
        {
            log.fatal("(MockRestImpl) -> (getProductDetail): Current thread was interrupted: ", e);
            Thread.currentThread().interrupt();
            throw new UnexpectedDomainException(e);
        }
        catch (IOException | URISyntaxException exception)
        {
            log.error("(MockRestImpl) -> (getProductDetail): There was an exception trying to connect with mock service", exception);

            // TODO: in the future create sometype of TODOTASK (Jira i.e) for Service Support

            // we are returning the default behavior, but we could throw an UnexpectedException or similar.
            // we only need to define what we want to do on yellow paths
            return Optional.empty();
        }
    }
}
