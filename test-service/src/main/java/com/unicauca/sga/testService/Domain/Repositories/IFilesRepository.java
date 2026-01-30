package com.unicauca.sga.testService.Domain.Repositories;

public interface IFilesRepository {
    boolean testConnection();
    String uploadFile(byte[] file, String filename);
    boolean deleteFile(String filename);
}
