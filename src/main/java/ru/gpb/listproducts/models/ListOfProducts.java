package ru.gpb.listproducts.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@Document(collection = "list_of_products")
public class ListOfProducts {

    @Transient
    public static final String SEQUENCE_NAME = "list_of_products_sequence";

    @Id
    private long id;

    private String name;

    @DocumentReference
    private List<Product> products;
}
