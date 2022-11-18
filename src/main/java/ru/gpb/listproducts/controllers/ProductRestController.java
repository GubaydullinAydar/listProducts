package ru.gpb.listproducts.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gpb.listproducts.dto.ProductDto;
import ru.gpb.listproducts.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "Product")
public class ProductRestController {

    private final ProductService productService;

    @Operation(summary = "Get all products")
    @GetMapping(value = "")
    public List<ProductDto> getAllProducts() {
        return productService.findAllProducts();
    }

    @Operation(summary = "Save product")
    @PostMapping(value = "")
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }
}
