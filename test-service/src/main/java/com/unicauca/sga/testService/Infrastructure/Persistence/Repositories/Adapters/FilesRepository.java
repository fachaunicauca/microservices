package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.unicauca.sga.testService.Domain.Repositories.IFilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FilesRepository implements IFilesRepository {

    private final Cloudinary cloudinary;
    private final String folder = "test-service/";

    @Override
    public boolean testConnection() {
        try {
            ApiResponse result = cloudinary.api().ping(ObjectUtils.emptyMap());
            return result.get("status").equals("ok");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "El repositorio de almacenamiento de archivos no se encuentra disponible."
            );
        }
    }

    @Override
    public String uploadFile(byte[] file, String fileId) {
        Map<?, ?> result;
        try {
            result = cloudinary.uploader().upload(
                    file,
                    ObjectUtils.asMap(
                            "resource_type", "auto",
                            "folder", folder,
                            "overwrite", true,
                            "public_id", fileId
                    )
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ocurrió un error al subir el archivo al repositorio de almacenamiento."
            );
        }
        return result.get("secure_url").toString();
    }

    public boolean deleteFile(String fileId) {
        try {
            Map<?, ?> result = cloudinary.uploader().destroy(folder + fileId, ObjectUtils.asMap());
            String deleteResult = (String) result.get("result");
            return deleteResult.equals("ok");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ocurrió un error al eliminar el archivo del repositorio de almacenamiento."
            );
        }
    }
}
