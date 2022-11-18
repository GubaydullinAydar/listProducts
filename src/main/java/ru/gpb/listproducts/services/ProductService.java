package ru.gpb.listproducts.services;

import ru.gpb.listproducts.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAllProducts();

    ProductDto saveProduct(ProductDto productDto);
}
