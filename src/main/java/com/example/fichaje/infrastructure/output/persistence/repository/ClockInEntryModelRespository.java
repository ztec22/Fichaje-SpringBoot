package com.example.fichaje.infrastructure.output.persistence.repository;

import com.example.fichaje.domain.model.ClockInEntry;
import com.example.fichaje.infrastructure.output.persistence.model.ClockInEntryModel;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClockInEntryModelRespository extends MongoRepository<ClockInEntryModel, String> {

    @Aggregation(pipeline = {
            "{ $addFields: { typeObjectId: { $toObjectId: '$type_id' } } }",
            "{ $lookup: { " +
                    "from: 'clock_in_types', " +
                    "localField: 'typeObjectId', " +
                    "foreignField: '_id', " +
                    "as: 'clockInType'" +
                "} " +
            "}",
            "{ $unwind: { path: '$clockInType', preserveNullAndEmptyArrays: true } }"
    })
    List<ClockInEntry> findEntries();

}
