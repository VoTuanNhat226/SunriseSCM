package com.vtn.dto.cart;

import com.vtn.dto.product.ProductResponseForList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailsResponse {

    private Float quantity;

    private BigDecimal unitPrice;

    private ProductResponseForList product;
}
