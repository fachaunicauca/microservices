package com.unicauca.sga.testService.Aplication.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile file, String test_guide_id) throws IOException {
        String formattedId = test_guide_id.replace(" ", "_");

        Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "auto",
                        "folder", "test-service",
                        "overwrite", true,
                        "public_id", formattedId
                )
        );
        return result.get("secure_url").toString();
    }

    public boolean testConnection() {
        try {
            ApiResponse result = cloudinary.api().ping(ObjectUtils.emptyMap());
            return result.get("status").equals("ok");
        } catch (Exception e) {
            System.err.println("Error al conectar con Cloudinary: " + e.getMessage());
            return false;
        }
    }

    public String deleteFile(String file_id) {
        try {

            @SuppressWarnings("unchecked")
            Map<String, Object> result = cloudinary.uploader().destroy(file_id, ObjectUtils.asMap(
                    "resource_type", "raw",
                    "folder", "test-service"
            ));
            String deleteResult = (String) result.get("result");
            if (deleteResult.equals("ok") ) {
                return "ok";
            }
            return deleteResult;

        } catch (Exception e) {
            throw new RuntimeException("Error deleting file from Cloudinary: " + e.getMessage(), e);
        }
    }
}
