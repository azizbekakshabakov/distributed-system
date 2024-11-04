package com.task2.micro01.micro02.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
