package com.vtn.dto.statistics;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatusReportEntry {

    private Long productId;

    private String productName;

    private String productUnit;

    private Float productQuantity;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expiryDate;
}
