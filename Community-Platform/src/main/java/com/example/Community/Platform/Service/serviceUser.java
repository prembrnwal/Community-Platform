package com.example.Community.Platform.Service;

import com.example.Community.Platform.DTO.Login_dto;
import com.example.Community.Platform.Entity.Login_User;
import com.example.Community.Platform.Repository.repo_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class serviceUser {

    @Autowired
    repo_User repo;

    public Login_User addUser(Login_User user){
        return repo.save(user);
    }

    public Boolean loginUser(Login_dto LoginRequest){
        Optional<Login_User> user = repo.findById(LoginRequest.getUserId());
        if (!user.isPresent()){
            return false;
        }
        Login_User user1 = user.get();

        if(!user1.getPassword().equals(LoginRequest.getPassword())) {
            return false;
        }

        return true;
    }
}