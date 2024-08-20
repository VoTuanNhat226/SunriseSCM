package com.vtn.dto.order.request;

import com.vtn.enums.OrderStatusEnum;
import com.vtn.enums.OrderTypeEnum;
import com.vtn.enums.ShippingStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private Long id;
    private Date orderDate;
    private OrderStatusEnum status;
    private OrderTypeEnum type;
    private Long userId;
}
