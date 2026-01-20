package com.unicauca.sga.testService.Domain.Repositories;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFilesRepository {
    boolean testConnection();
    String uploadFile(MultipartFile file, String filename);
    boolean deleteFile(String filename);
}
