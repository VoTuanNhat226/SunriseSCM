package com.vtn.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShippingStatusEnum {
    SHIPPED,
    IN_TRANSIT,
    DELIVERED,
    RETURNED;
}
