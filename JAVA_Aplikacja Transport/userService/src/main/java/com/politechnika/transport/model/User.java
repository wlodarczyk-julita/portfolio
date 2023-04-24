package com.politechnika.transport.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;


    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;
    @DBRef
    private List<UserRole> roles;
    private float wallet = 0;
    public User(String username, String email, String password, float wallet) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.wallet = wallet;
    }


}
