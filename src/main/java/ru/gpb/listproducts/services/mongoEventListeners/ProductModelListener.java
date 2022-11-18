package ru.gpb.listproducts.services.mongoEventListeners;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Service;
import ru.gpb.listproducts.models.Product;
import ru.gpb.listproducts.services.SequenceGeneratorService;

@Service
@RequiredArgsConstructor
public class ProductModelListener extends AbstractMongoEventListener<Product> {

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Product> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGeneratorService.getNextSequence(Product.SEQUENCE_NAME));
        }
    }
}
