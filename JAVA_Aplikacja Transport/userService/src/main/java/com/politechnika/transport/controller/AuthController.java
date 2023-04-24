package com.politechnika.transport.controller;


import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.politechnika.transport.dto.UserCreateDto;
import com.politechnika.transport.dto.UserLoginDto;
import com.politechnika.transport.model.*;
import com.politechnika.transport.repository.UserRepository;
import com.politechnika.transport.repository.UserRoleRepository;
import com.politechnika.transport.security.jwt.JwtUtils;
import com.politechnika.transport.security.services.UserDetailsImpl;
import com.politechnika.transport.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginDto loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        if (userRepository.existsByUsername(userCreateDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(userCreateDto.getUsername(),
                userCreateDto.getEmail(),
                encoder.encode(userCreateDto.getPassword()),
                0);

        List<String> rolesName = userCreateDto.getRoles();
        List<UserRole> roles = new ArrayList<>();

            if (rolesName == null) {
                UserRole userRole = userRoleRepository.findByName("ROLE_USER");
                roles.add(userRole);
            } else {
                rolesName.forEach(role -> {
                    switch (role) {
                        case "ROLE_ADMIN":
                            UserRole adminRole = userRoleRepository.findByName("ROLE_ADMIN");
                            roles.add(adminRole);
                            break;
                        default:
                            UserRole userRole = userRoleRepository.findByName("ROLE_USER");
                            roles.add(userRole);
                    }
                });
        }

            user.setRoles(roles);
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }
}

