package com.politechnika.transport.repository;
import com.politechnika.transport.model.Payments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends MongoRepository<Payments, String> {

    @Query(value="{'_id' : ?0}", delete = true)
    public void deleteById(String id);
    List<Payments> findAll();

}
