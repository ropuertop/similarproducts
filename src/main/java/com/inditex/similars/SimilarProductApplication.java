package com.inditex.similars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings("NonFinalUtilityClass")
public class SimilarProductApplication
{
    /**
     * Main thread of the similar products service.
     * @param args similar products service arguments
     */
    public static void main(String[] args)
    {
        SpringApplication.run(SimilarProductApplication.class, args);
    }
}
