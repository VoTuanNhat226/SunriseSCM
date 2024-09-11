package com.vtn.dto.rating;

import com.vtn.pojo.Supplier;
import com.vtn.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {

    private Long id;

    private BigDecimal rating;

    private String content;

    private String criteria;

    private Supplier supplier;

    private User user;
}
