package ru.gpb.listproducts.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.gpb.listproducts.dto.ListOfProductsDto;
import ru.gpb.listproducts.models.ListOfProducts;
import ru.gpb.listproducts.models.Product;
import ru.gpb.listproducts.repositories.ListOfProductsRepository;
import ru.gpb.listproducts.repositories.ProductRepository;

import java.util.Collections;

import static java.util.Objects.nonNull;
import static ru.gpb.listproducts.services.MapperUtils.listOfProductsModelToDto;

@Service
@RequiredArgsConstructor
public class ListOfProductsServiceImpl implements ListOfProductsService {

    Logger log = LoggerFactory.getLogger(ListOfProductsServiceImpl.class);

    private final ListOfProductsRepository listOfProductsRepository;
    private final ProductRepository productRepository;

    @Override
    public ListOfProductsDto findAllListOfProducts(Long listId) {
        return listOfProductsModelToDto(listOfProductsRepository.findById(listId).get());
    }

    @Override
    public ListOfProductsDto saveListOfProducts(ListOfProductsDto listOfProductsDto) {
        log.debug("ListOfProductsDto to save id: {}, name: {}", listOfProductsDto.getId(), listOfProductsDto.getName());

        ListOfProducts listOfProducts;

        if (nonNull(listOfProductsDto.getId()) &&
                listOfProductsRepository.existsById(listOfProductsDto.getId())) {
            listOfProducts = listOfProductsRepository.findById(listOfProductsDto.getId()).get();
        } else {
            listOfProducts = new ListOfProducts();
        }

        listOfProducts.setName(listOfProductsDto.getName());
        listOfProductsRepository.save(listOfProducts);

        return listOfProductsModelToDto(listOfProducts);
    }

    @Override
    public ListOfProductsDto saveProductToListOfProducts(Long listId, Long productId) {
        log.debug("Product id: {} save to list id: {}", productId, listId);

        if (!listOfProductsRepository.existsById(listId) || !productRepository.existsById(productId)) {
            log.error("List with id: {} or product with id: {} not exist!", listId, productId);
            throw new IllegalArgumentException(
                    String.format("List with id: %s or product with id: %s not exist!", listId, productId));
        }

        ListOfProducts listOfProducts = listOfProductsRepository.findById(listId).get();
        Product product = productRepository.findById(productId).get();

        if (nonNull(listOfProducts.getProducts())) {
            listOfProducts.getProducts().add(product);
        } else {
            listOfProducts.setProducts(Collections.singletonList(product));
        }

        listOfProductsRepository.save(listOfProducts);
        return listOfProductsModelToDto(listOfProducts);
    }
}
