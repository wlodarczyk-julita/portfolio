package com.politechnika.transport.controller;

import com.politechnika.transport.dto.UserRoleCreateDto;
import com.politechnika.transport.model.UserRole;
import com.politechnika.transport.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;
    @PostMapping
    public UserRole addUserRole(@Valid @RequestBody UserRole userRole){
        return userRoleService.addUserRole(userRole);
    }
}
