package com.vtn.repository;

import com.vtn.dto.statistics.ProductStatisticsForRevenueEntry;
import com.vtn.dto.statistics.ProductStatusReportEntry;
import com.vtn.dto.statistics.InventoryStatusReportEntry;
import com.vtn.dto.statistics.WarehouseStatusReportEntry;
import com.vtn.dto.statistics.CategoryStatisticsForeRevenueEntry;
import com.vtn.dto.statistics.RevenueStatisticsForDashBoardEntry;

import java.util.List;

public interface StatisticsRepository {

    List<Object[]> generateStatisticsRevenueByPeroid(int year, String period);

    List<ProductStatisticsForRevenueEntry> findProductsOfRevenueByPeroid(int year, String period);

    List<CategoryStatisticsForeRevenueEntry> findCategoriesOfRevenueByPeroid(int year, String period);

    RevenueStatisticsForDashBoardEntry generateStatisticsRevenueByWeeks(int days);

    List<Object[]> generateSupplierPerformanceReport(Long supplierId, Integer year);

    List<WarehouseStatusReportEntry> generateWarehouseStatusReport();

    List<InventoryStatusReportEntry> generateInventoryStatusReportOfWarehouse(Long warehouseId);

    List<Object[]> generateStatisticsProductsStatusOfInventory(Long inventoryId);

    List<ProductStatusReportEntry> findProductsOfInventoryByStatus(Long inventoryId, String status);
}
