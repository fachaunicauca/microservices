package com.unicauca.sga.educatorService.infrastructure.controller;

import com.unicauca.sga.educatorService.infrastructure.controller.DTO.CreateUserDTO;
import com.unicauca.sga.educatorService.infrastructure.persitence.RepositoryImpl.ManagementUsersImpl;
import com.unicauca.sga.educatorService.service.ExcelUserParser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ManagementUsersControllers {

    private final ManagementUsersImpl userMgmt;
    private final ExcelUserParser excelUserParser;

    @PostMapping("/register-student")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<String> registerStudent(@RequestBody CreateUserDTO request,
                                                  @AuthenticationPrincipal Jwt jwt) {
        String token = jwt.getTokenValue();
        userMgmt.registerUser(request, token);
        return ResponseEntity.ok("Estudiante creado correctamente");
    }

    @PostMapping("/upload-students")
    public ResponseEntity<Map<String, Object>> uploadStudents(@RequestParam("file") MultipartFile file,
                                                @AuthenticationPrincipal Jwt jwt ) {
        try {
            List<CreateUserDTO> students = excelUserParser.parse(file);
            String adminToken = jwt.getTokenValue();

            List<String> created = new ArrayList<>();
            List<String> duplicated = new ArrayList<>();
            List<String> failed = new ArrayList<>();

            for (CreateUserDTO student : students) {
                try {
                    boolean success = userMgmt.registerStudentIfNotExists(student, adminToken);
                    if (success) {
                        created.add(student.getUsername());
                    } else {
                        duplicated.add(student.getUsername());
                    }
                } catch (Exception e) {
                    failed.add(student.getUsername() + " (" + e.getMessage() + ")");
                }
            }

            Map<String, Object> result = new HashMap<>();
            result.put("created", created);
            result.put("duplicated", duplicated);
            result.put("failed", failed);

            return ResponseEntity.ok(result);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Archivo inválido: " + e.getMessage()));
        }
    }
}
