package ru.gpb.listproducts.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.gpb.listproducts.models.Sequence;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class SequenceGeneratorService {

    Logger log = LoggerFactory.getLogger(SequenceGeneratorService.class);

    private final MongoOperations mongoOperations;

    public long getNextSequence(String seqName) {
        log.debug("Sequence name: {}", seqName);

        Sequence counter = mongoOperations.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                Sequence.class
        );

        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
