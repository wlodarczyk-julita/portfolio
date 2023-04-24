package com.politechnika.transport.repository;

import com.politechnika.transport.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends MongoRepository<Ticket, String> {

    @Query(value="{'_id' : ?0}", delete = true)
    public void deleteById(String id);
    List<Ticket> findAll();

}
