package com.inditex.similars.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public final class DTOSimilarProducts {
    private List<DTOProductDetail> similarProducts;
}
