package com.vtn.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vtn.enums.OrderStatus;
import com.vtn.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;

    private String orderNumber;

    private String invoiceNumber;

    private OrderType type;

    private OrderStatus status;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date orderDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expectedDelivery;

    private Set<OrderDetailsReponse> orderDetailsSet;
}
