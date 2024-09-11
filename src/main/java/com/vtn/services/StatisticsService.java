package com.vtn.services;

import com.vtn.dto.statistics.ProductStatisticsForRevenueEntry;
import com.vtn.dto.statistics.ProductStatusReportEntry;
import com.vtn.dto.statistics.InventoryStatusReportEntry;
import com.vtn.dto.statistics.WarehouseStatusReportEntry;
import com.vtn.dto.statistics.SupplierPerformanceReport;
import com.vtn.dto.statistics.CategoryStatisticsForeRevenueEntry;
import com.vtn.dto.statistics.ProductStatusStatisticEntry;
import com.vtn.dto.statistics.RevenueStatisticsForDashBoardEntry;

import java.util.List;
import java.util.Map;

public interface StatisticsService {

    Map<String, Object> getStatisticsRevenueByPeroid(int year, String period);

    List<ProductStatisticsForRevenueEntry> findProductsOfRevenueByPeroid(int year, String period);

    List<CategoryStatisticsForeRevenueEntry> findCategoriesOfRevenueByPeroid(int year, String period);

    RevenueStatisticsForDashBoardEntry getStatisticsRevenueByWeeks(int days);

    SupplierPerformanceReport getSupplierPerformanceReport(Long supplierId, Integer year);

    List<WarehouseStatusReportEntry> getWarehouseStatusReport();

    List<InventoryStatusReportEntry> getInventoryStatusReportOfWarehouse(Long warehouseId);

    List<ProductStatusStatisticEntry> getStatisticsProductsStatusOfInventory(Long inventoryId);

    List<ProductStatusReportEntry> findProductsOfInventoryByStatus(Long inventoryId, String status);
}
