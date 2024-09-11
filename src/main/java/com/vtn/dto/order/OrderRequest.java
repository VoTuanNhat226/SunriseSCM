package com.vtn.dto.order;

import com.vtn.enums.OrderStatus;
import com.vtn.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotNull(message = "{order.type.notNull}")
    private OrderType type;

    private OrderStatus status;

    @Valid
    private Set<OrderDetailsRequest> orderDetails;

    private Long inventoryId; // Cho đơn hàng nhập

    private Date createdAt;

    private Boolean paid;
}
