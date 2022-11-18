package ru.gpb.listproducts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private int kcal;
}
