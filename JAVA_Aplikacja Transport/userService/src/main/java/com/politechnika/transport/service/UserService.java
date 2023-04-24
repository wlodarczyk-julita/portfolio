package com.politechnika.transport.service;

import com.politechnika.transport.model.User;

import java.util.List;

public interface UserService {
    public User getUser(String userId);
    List<User> userList();
}
