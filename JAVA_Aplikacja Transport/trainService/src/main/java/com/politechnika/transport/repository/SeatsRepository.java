package com.politechnika.transport.repository;

import com.politechnika.transport.model.Connection;
import com.politechnika.transport.model.Seats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends MongoRepository<Seats, String> {

}
