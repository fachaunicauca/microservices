package com.unicauca.sga.educatorService.infrastructure.persitence.RepositoryImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.sga.educatorService.core.repositoires.IManagementUsersRepository;
import com.unicauca.sga.educatorService.infrastructure.controller.DTO.CreateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ManagementUsersImpl implements IManagementUsersRepository {

    @Value("${keycloak.server-url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.student-role-id}")
    private String studentRoleId;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void registerUser(CreateUserDTO request, String adminToken) {
        String url = keycloakUrl + "/admin/realms/" + realm + "/users";

        Map<String, Object> payload = new HashMap<>();
        payload.put("username", request.getUsername());
        payload.put("enabled", true);
        payload.put("emailVerified", true);
        payload.put("firstName", request.getFirstName());
        payload.put("lastName", request.getLastName());
        payload.put("email", request.getEmail());
        payload.put("credentials", List.of(Map.of(
                "type", "password",
                "value", request.getPassword(),
                "temporary", false
        )));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(adminToken);

        HttpEntity<String> entity;
        try {
            String json = new ObjectMapper().writeValueAsString(payload);
            entity = new HttpEntity<>(json, headers);
        } catch (Exception e) {
            throw new RuntimeException("Error building user payload", e);
        }

        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Failed to create user: " + response.getStatusCode());
        }

        // Get location header for user ID
        String location = response.getHeaders().getLocation().toString();
        String userId = location.substring(location.lastIndexOf('/') + 1);

        // Assign role
        assignStudentRole(userId, adminToken);

        if (response.getStatusCode() == HttpStatus.CREATED) {
        } else {
            throw new RuntimeException("Failed to assign role to user: " + response.getStatusCode());
        }
    }

    private void assignStudentRole(String userId, String token) {
        String url = keycloakUrl + "/admin/realms/" + realm + "/users/" + userId + "/role-mappings/realm";

        Map<String, Object> role = Map.of(
                "id", studentRoleId,
                "name", "STUDENT"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<Object> entity = new HttpEntity<>(List.of(role), headers);
        restTemplate.postForEntity(url, entity, Void.class);
    }

    @Override
    public boolean userExists(String username, String email, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String urlUsername = String.format("%s/admin/realms/%s/users?username=%s", keycloakUrl, realm, username);
        String urlEmail = String.format("%s/admin/realms/%s/users?email=%s", keycloakUrl, realm, email);

        try {
            ResponseEntity<String> resUser = restTemplate.exchange(urlUsername, HttpMethod.GET, entity, String.class);
            ResponseEntity<String> resEmail = restTemplate.exchange(urlEmail, HttpMethod.GET, entity, String.class);
            return !resUser.getBody().equals("[]") || !resEmail.getBody().equals("[]");
        } catch (HttpClientErrorException e) {
            return false;
        }
    }

    @Override
    public boolean registerStudentIfNotExists(CreateUserDTO dto, String adminToken) {
        if (userExists(dto.getUsername(), dto.getEmail(), adminToken)) {
            return false;
        }

        registerUser(dto,  adminToken);
        return true;
    }
}
