package com.politechnika.transport.service;

import com.politechnika.transport.controller.UserRoleController;
import com.politechnika.transport.dto.UserRoleCreateDto;
import com.politechnika.transport.model.UserRole;
import com.politechnika.transport.repository.UserRoleRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }
    public UserRole addUserRole(UserRole userRole){
        return userRoleRepository.save(userRole);
    }

}
