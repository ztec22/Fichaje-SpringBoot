package com.example.fichaje.infrastructure.persistence.repository;

import com.example.fichaje.infrastructure.persistence.model.ClockInTypeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClockInTypeModelRespository extends MongoRepository<ClockInTypeModel, String> {
}
