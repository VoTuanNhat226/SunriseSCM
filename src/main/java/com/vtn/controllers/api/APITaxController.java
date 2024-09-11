package com.vtn.controllers.api;

import com.vtn.dto.tax.TaxResponse;
import com.vtn.services.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/taxes", produces = "application/json; charset=UTF-8")
public class APITaxController {

    private final TaxService taxService;

    @GetMapping
    public ResponseEntity<?> getTaxes(@RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        List<TaxResponse> taxes = this.taxService.getAllTaxResponse(params);

        return ResponseEntity.ok(taxes);
    }
}
