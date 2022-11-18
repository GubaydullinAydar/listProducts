package ru.gpb.listproducts.services;

import ru.gpb.listproducts.dto.ListOfProductsDto;

public interface ListOfProductsService {
    ListOfProductsDto findAllListOfProducts(Long listId);

    ListOfProductsDto saveListOfProducts(ListOfProductsDto listOfProductsDto);

    ListOfProductsDto saveProductToListOfProducts(Long listId, Long productId);
}
