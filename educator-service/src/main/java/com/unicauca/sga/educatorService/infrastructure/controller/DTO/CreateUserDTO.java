package com.unicauca.sga.educatorService.infrastructure.controller.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateUserDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
