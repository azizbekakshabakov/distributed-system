package com.task2.micro01.micro02.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
}
