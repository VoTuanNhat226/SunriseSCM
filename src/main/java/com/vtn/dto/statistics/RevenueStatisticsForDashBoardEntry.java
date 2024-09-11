package com.vtn.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevenueStatisticsForDashBoardEntry {

    private BigDecimal totalAmount;

    private Long totalOrders;

    private Long totalProducts;

    private List<DailyDetail> details;

    public RevenueStatisticsForDashBoardEntry(BigDecimal totalAmount, Long totalOrders, Long totalProducts) {
        this.totalAmount = totalAmount;
        this.totalOrders = totalOrders;
        this.totalProducts = totalProducts;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DailyDetail {

        private int day;

        private BigDecimal amount;
    }
}