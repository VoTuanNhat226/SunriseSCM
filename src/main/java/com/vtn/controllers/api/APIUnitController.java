package com.vtn.controllers.api;

import com.vtn.dto.unit.UnitResponse;
import com.vtn.services.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/units", produces = "application/json; charset=UTF-8")
public class APIUnitController {

    private final UnitService unitService;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        List<UnitResponse> units = this.unitService.getAllUnitResponse(params);

        return ResponseEntity.ok(units);
    }
}
