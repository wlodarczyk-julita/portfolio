package com.politechnika.transport.repository;

import com.politechnika.transport.model.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRoleRepository extends MongoRepository<UserRole, String> {
    UserRole findByName(String name);
}
