package com.example.Community.Platform.Controller;

import com.example.Community.Platform.DTO.Login_dto;
import com.example.Community.Platform.Entity.Login_User;
import com.example.Community.Platform.Service.serviceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login_cont {

        @Autowired
        serviceUser userservice;

        @PostMapping("/adduser")
        public Login_User adduser(@RequestBody Login_User user){
            return userservice.addUser(user);
        }
        @PostMapping("/loginUser")
        public Boolean loginUser(@RequestBody Login_dto LoginRequest){
            return userservice.loginUser(LoginRequest);
        }

        @PutMapping("/loginUser")
        public Boolean loginUserPut(@RequestBody Login_dto LoginRequest) {
            return userservice.loginUser(LoginRequest);
        }


}
