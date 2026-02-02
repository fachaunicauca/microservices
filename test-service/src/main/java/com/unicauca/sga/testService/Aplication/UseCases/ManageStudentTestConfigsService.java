package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Domain.Enums.AttemptRequestStatus;
import com.unicauca.sga.testService.Domain.Exceptions.HasRemainingAttemptsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Repositories.IStudentTestConfigRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManageStudentTestConfigsService {
    private final IStudentTestConfigRepository studentTestConfigRepository;
    private final ITestRepository testRepository;

    @Transactional(readOnly = true)
    public Page<StudentTestConfig> getPriorityStudentTestConfigsPaged(int testId, Pageable pageable){
        if (!testRepository.isPresent(testId)){
            throw new NotFoundException("La evaluación con id "+ testId+ " ya no existe");
        }

        Page<StudentTestConfig> page = studentTestConfigRepository.getConfigsWithPendingAttemptRequest(testId, pageable);

        if(page.isEmpty()){
            throw new NotFoundException("Ningún estudiante ha solicitado restablecer sus intentos");
        }

        return page;
    }

    @Transactional(readOnly = true)
    public Page<StudentTestConfig> getStudentTestConfigsPaged(int testId, Pageable pageable){
        if (!testRepository.isPresent(testId)){
            throw new NotFoundException("La evaluación con id "+ testId+ " ya no existe");
        }

        Page<StudentTestConfig> page = studentTestConfigRepository.getConfigsWithoutAttemptRequest(testId, pageable);

        if(page.isEmpty()){
            throw new NotFoundException("Ningún estudiante ha iniciado esta evaluación");
        }

        return page;
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

        // Marcar como solicitada la configuracion para que sea priotaria
        studentTestConfig.setAttemptRequestStatus(AttemptRequestStatus.REQUESTED);

        studentTestConfigRepository.save(studentTestConfig);
    }
}
