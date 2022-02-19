package com.inditex.similars.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DTOProductDetail {
    private String id;
    private String name;
    private Double price;
    private Boolean availability;
}
