package com.vtn.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vtn.dto.category.CategoryResponse;
import com.vtn.dto.supplier.SupplierDTO;
import com.vtn.dto.tag.TagResponse;
import com.vtn.dto.unit.UnitResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseForDetails {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String image;

    private SupplierDTO supplier;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date expiryDate;

    private UnitResponse unit;

    private CategoryResponse category;

    private Set<TagResponse> tagSet;
}
