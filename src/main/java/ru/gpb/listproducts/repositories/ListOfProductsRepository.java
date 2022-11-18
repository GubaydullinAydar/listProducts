package ru.gpb.listproducts.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.gpb.listproducts.models.ListOfProducts;

@Repository
public interface ListOfProductsRepository extends MongoRepository<ListOfProducts, Long> {
}
