package com.vtn.controllers.api;

import com.vtn.dto.category.CategoryResponse;
import com.vtn.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/categories", produces = "application/json; charset=UTF-8")
public class APICategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false, defaultValue = "") Map<String, String> params) {
        List<CategoryResponse> categories = this.categoryService.getAllCategoryResponse(params);

        return ResponseEntity.ok(categories);
    }
}
