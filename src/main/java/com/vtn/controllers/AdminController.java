package com.vtn.controllers;

import com.vtn.services.OrderService;
import com.vtn.services.StatisticsService;
import com.vtn.services.SupplierService;
import com.vtn.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final StatisticsService statisticsService;
    private final SupplierService supplierService;
    private final OrderService orderService;

    @GetMapping("/login")
    public String login(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/";
    }

    @GetMapping(path = "/")
    public String dashBoard(Model model) {
        model.addAttribute("revenueCurrentWeek", this.statisticsService.getStatisticsRevenueByWeeks(Constants.CURRENT_WEEK));
        model.addAttribute("revenueLastWeek", this.statisticsService.getStatisticsRevenueByWeeks(Constants.LAST_WEEK));
        model.addAttribute("recentlyOrders", this.orderService.findRecentlyOrders());

        return "dashboard";
    }

    @GetMapping(path = "/admin/statistics")
    public String statistics() {
        return "statistics";
    }

    @GetMapping(path = "/admin/statistics/revenue")
    public String statisticsRevenue() {
        return "statistics_revenue";
    }

    @GetMapping(path = "/admin/statistics/supplier-performance")
    public String supplierPerformanceReport(Model model) {
        model.addAttribute("suppliers", this.supplierService.findAllWithFilter(null));

        return "statistics_performance";
    }

    @GetMapping(path = "/admin/inventory-report")
    public String inventoryStatusReport(Model model) {
        model.addAttribute("warehouseCapacityReport", this.statisticsService.getWarehouseStatusReport());

        return "statistics_inventory";
    }
}
