package unicauca.edu.co.laboratory.inventory_service.application.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) throws IOException {
        Map<?,?> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "auto",
                        "folder", "inventory-service",
                        "overwrite", true,
                        "public_id", Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_") // Reemplaza espacios por guiones bajos
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
}
