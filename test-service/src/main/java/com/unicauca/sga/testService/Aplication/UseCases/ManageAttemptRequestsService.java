package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Domain.Enums.AttemptRequestStatus;
import com.unicauca.sga.testService.Domain.Exceptions.HasRemainingAttemptsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Repositories.IStudentTestConfigRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManageAttemptRequestsService {
    private final IStudentTestConfigRepository studentTestConfigRepository;
    private final ITestRepository testRepository;

    @Transactional(readOnly = true)
    public Iterable<StudentTestConfig> getPendingAttemptRequestPaged(int testId, int page, int size){
        if (!testRepository.isPresent(testId)){
            throw new NotFoundException("La evaluación con id "+ testId+ " ya no existe");
        }

        Iterable<StudentTestConfig> pagedConfigs = studentTestConfigRepository.getConfigsWithPendingAttemptRequest(testId, page, size);

        if(!pagedConfigs.iterator().hasNext()){
            throw new NotFoundException("Ningún estudiante ha solicitado restablecer sus intentos");
        }

        return pagedConfigs;
    }

    @Transactional
    public void requestAttempts(String studentEmail, int testId){
        StudentTestConfig studentTestConfig = studentTestConfigRepository.getStudentTestConfig(studentEmail, testId).orElseThrow(
                () -> new NotFoundException("No has presentado intentos de esta evaluación")
        );

        // Verificar si tiene intentos disponibles
        if(studentTestConfig.hasRemainingAttempts()){
            throw new HasRemainingAttemptsException(
                    "No puede solicitar mas intentos porque aún tiene "+ (
                    studentTestConfig.getRemainingAttempts() > 1 ?
                    " intentos disponibles":
                    " intento disponible"));
        }

        // Marcar como solicitada
        studentTestConfig.setAttemptRequestStatus(AttemptRequestStatus.REQUESTED);

        studentTestConfigRepository.save(studentTestConfig);
    }

    @Transactional
    public void resetAttempts(String studentEmail, int testId){
        StudentTestConfig studentTestConfig = studentTestConfigRepository.getStudentTestConfig(studentEmail, testId).orElseThrow(
                () -> new NotFoundException("La configuración del estudiante en esta evaluación ya no existe")
        );

        // Desmarcar solicitud
        studentTestConfig.setAttemptRequestStatus(AttemptRequestStatus.NOT_REQUESTED);

        // Restablecer intentos
        studentTestConfig.setAttemptsUsed(0);

        studentTestConfigRepository.save(studentTestConfig);
    }
}
