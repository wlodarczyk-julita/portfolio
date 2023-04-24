package com.politechnika.transport.repository;

import com.politechnika.transport.model.Connection;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends MongoRepository<Connection, String> {

    public boolean existsByTrainName(String trainName);
}
