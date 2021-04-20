package com.example.springbook.domain.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    String id;
    String name;
    String password;

    Level level;
    int login;
    int recommend;
}
