package com.politechnika.transport.service;

import com.politechnika.transport.exception.UserError;
import com.politechnika.transport.exception.UserException;
import com.politechnika.transport.model.User;
import com.politechnika.transport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUser(String userId){
        return userRepository.findById(userId)
                .orElseThrow(() ->new UserException(UserError.USER_NOT_FOUND));
    }

    public List<User> userList(){
        return userRepository.findAll();
    }
}
