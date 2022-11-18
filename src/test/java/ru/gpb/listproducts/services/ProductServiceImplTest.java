package ru.gpb.listproducts.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gpb.listproducts.dto.ProductDto;
import ru.gpb.listproducts.models.Product;
import ru.gpb.listproducts.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @Test
    void should_return_product_dto_list() {
        when(productRepository.findAll()).thenReturn(initProductModelList());
        assertEquals(2, productService.findAllProducts().size());
    }

    private List<Product> initProductModelList() {
        Product product1 = new Product();
        product1.setName("name1");
        product1.setDescription("desc1");

        Product product2 = new Product();
        product2.setName("name2");
        product2.setDescription("desc2");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        return productList;
    }

    @Test
    void should_save_product_and_return_dto() {
        Product product = new Product();
        product.setName("name");
        product.setDescription("desc");

        when(productRepository.save(product)).thenReturn(product);

        ProductDto saveProduct = productService.saveProduct(new ProductDto(null, "name", "desk", 0));
        assertEquals(product.getName(), saveProduct.getName());
    }
}