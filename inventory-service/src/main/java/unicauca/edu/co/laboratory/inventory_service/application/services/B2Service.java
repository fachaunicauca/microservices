package unicauca.edu.co.laboratory.inventory_service.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.config.B2StorageConfig;

@Service
@RequiredArgsConstructor
public class B2Service {
    private final S3Client s3Client;
    private final B2StorageConfig config;

    public boolean testConnection() {
        try {
            ListBucketsResponse buckets = s3Client.listBuckets();
            return buckets.buckets().stream()
                    .anyMatch(b -> b.name().equals(config.getBucketName()));
        } catch (S3Exception e) {
            System.err.println("Error al conectar con Backblaze B2: " + e.awsErrorDetails().errorMessage());
            return false;
        }
    }
}
