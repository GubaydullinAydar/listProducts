package ru.gpb.listproducts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListOfProductsDto {

    private Long id;
    private String name;
    private List<ProductDto> products;
    private int kcalSum;
}
