package com.inditex.similars.core.model;

import com.inditex.similars.core.utils.IValidator;

import java.util.Collection;

/**
 * This interface is in charge of providing every added
 * functionality related to the {@link Product} domain class
 *
 * @author ropuertop
 */
public interface ProductFunctionality extends IValidator {

    /**
     * This method is in charge of updating the current similar products
     *
     * @param similarProducts new {@link Product} that are similar
     */
    void updateSimilarProducts(Collection<Product> similarProducts);
}
