package com.example.Community.Platform.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class Login_User {
//
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        private String name;
//
//        @Column(unique = true, nullable = false)
//        private String email;
//
//        private String password;
//
//        private String bio;
//
//        private String interests;
//
//        private LocalDateTime createdAt;
//        private LocalDateTime updatedAt;




        @Id
        @Column(name = "email")
        private String email;

        @Column(name = "name")
        private String name;

        @Column(name = "password")
        private String password;

        public Login_User(String email, String name, String password) {
                this.email = email;
                this.name = name;
                this.password = password;
        }
        public Login_User(){

        }


}
