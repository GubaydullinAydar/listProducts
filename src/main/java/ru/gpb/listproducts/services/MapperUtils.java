package ru.gpb.listproducts.services;

import ru.gpb.listproducts.dto.ListOfProductsDto;
import ru.gpb.listproducts.dto.ProductDto;
import ru.gpb.listproducts.models.ListOfProducts;
import ru.gpb.listproducts.models.Product;

import java.util.Objects;
import java.util.stream.Collectors;

public class MapperUtils {

    public static ProductDto productModelToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getKcal()
        );
    }

    public static ListOfProductsDto listOfProductsModelToDto(ListOfProducts listOfProducts) {
        return new ListOfProductsDto(
                listOfProducts.getId(),
                listOfProducts.getName(),
                Objects.isNull(listOfProducts.getProducts()) ? null :
                        listOfProducts.getProducts()
                                .stream()
                                .map(MapperUtils::productModelToDto)
                                .collect(Collectors.toList()),
                Objects.isNull(listOfProducts.getProducts()) ? 0 :
                        listOfProducts.getProducts()
                                .stream()
                                .mapToInt(Product::getKcal)
                                .sum()
        );
    }
}
