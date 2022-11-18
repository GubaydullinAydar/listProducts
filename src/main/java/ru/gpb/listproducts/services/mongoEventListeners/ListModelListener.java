package ru.gpb.listproducts.services.mongoEventListeners;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Service;
import ru.gpb.listproducts.models.ListOfProducts;
import ru.gpb.listproducts.services.SequenceGeneratorService;

@RequiredArgsConstructor
@Service
public class ListModelListener  extends AbstractMongoEventListener<ListOfProducts> {

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ListOfProducts> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGeneratorService.getNextSequence(ListOfProducts.SEQUENCE_NAME));
        }
    }
}
