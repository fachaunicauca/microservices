package com.unicauca.sga.educatorService.core.repositoires;

import com.unicauca.sga.educatorService.infrastructure.controller.DTO.CreateUserDTO;

public interface IManagementUsersRepository {
    void registerUser(CreateUserDTO createUserDTO, String adminToken);
    boolean userExists(String username, String email, String token);
    boolean registerStudentIfNotExists(CreateUserDTO dto, String adminToken);
}
