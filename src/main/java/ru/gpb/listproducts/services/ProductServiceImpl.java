package ru.gpb.listproducts.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.gpb.listproducts.dto.ProductDto;
import ru.gpb.listproducts.models.Product;
import ru.gpb.listproducts.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(MapperUtils::productModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        logger.debug("ProductDto to save: {}", productDto.toString());

        Product product = new Product();
        if (productDto.getId() != null) {
            product.setId(productDto.getId());
        }
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setKcal(productDto.getKcal());
        productRepository.save(product);

        return MapperUtils.productModelToDto(product);
    }
}
