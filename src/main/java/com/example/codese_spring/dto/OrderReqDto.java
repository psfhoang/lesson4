package com.example.codese_spring.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderReqDto {
    private List<ProductReqDto> productReqDtos;

    public List<ProductReqDto> getProductReqDtos() {
        return productReqDtos;
    }

    public void setProductReqDtos(List<ProductReqDto> productReqDtos) {
        this.productReqDtos = productReqDtos;
    }
}
