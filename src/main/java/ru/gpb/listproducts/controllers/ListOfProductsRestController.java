package ru.gpb.listproducts.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gpb.listproducts.dto.ListOfProductsDto;
import ru.gpb.listproducts.services.ListOfProductsService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/list")
@Tag(name = "List of products")
public class ListOfProductsRestController {

    private final ListOfProductsService listOfProductsService;

    @Operation(summary = "Get list of products by list id", description = "")
    @GetMapping(value = "/{id}")
    public ListOfProductsDto getListOfProductsById(@PathVariable("id") Long id) {
        return listOfProductsService.findAllListOfProducts(id);
    }

    @Operation(summary = "Initial save list of products")
    @PostMapping(value = "")
    public ListOfProductsDto saveListOfProducts(@RequestBody ListOfProductsDto listOfProductsDto) {
        return listOfProductsService.saveListOfProducts(listOfProductsDto);
    }

    @Operation(summary = "Add product with current id to current list of products")
    @PutMapping(value = "/{id}/addProduct/{productId}")
    public ListOfProductsDto addProductToListOfProducts(@PathVariable("id") Long id,
                                                        @PathVariable("productId") Long productId) {
        return listOfProductsService.saveProductToListOfProducts(id, productId);
    }
}
