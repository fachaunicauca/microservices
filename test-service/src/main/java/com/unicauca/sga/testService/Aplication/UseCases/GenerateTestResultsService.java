package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Models.StudentTestResult;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Repositories.IStudentTestConfigRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import com.unicauca.sga.testService.Domain.Services.ICourseService;
import com.unicauca.sga.testService.Domain.Services.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GenerateTestResultsService {
    private final ICourseService courseService;
    private final IStudentTestConfigRepository studentTestConfigRepository;
    private final ITestRepository testRepository;
    private final IStudentService studentService;

    @Transactional(readOnly = true)
    public Iterable<StudentTestResult> getTestResultsPaged(int testId, int page, int size) {
        int courseId = testRepository.getTestById(testId).map(Test::getCourseId).orElseThrow(
                () -> new NotFoundException("La evaluación ya no existe")
        );

        // Si la evaluación pertenece a un curso (courseId !=0),
        // obtener los resultados para los estudiantes de ese curso
        if(courseId != 0){
            return getTestResultsForCoursePaged(testId, courseId, page, size);
        }
        // Si cualquier estudiante puede presentar la evaluación (courseId == 0)
        // obtener los resultados de todos los estudiantes
        return getTestResultsForGlobalPaged(testId, page, size);
    }

    private Iterable<StudentTestResult> getTestResultsForCoursePaged(int testId, int courseId, int page, int size) {
        // Obtener la página de estudiantes
        Iterable<StudentTestResult> studentsResults = courseService.getCourseStudents(courseId, page, size);

        if(!studentsResults.iterator().hasNext()){
            throw new NotFoundException("El curso no tiene estudiantes");
        }

        Collection<String> emails = new ArrayList<>();
        for(StudentTestResult studentTestResult : studentsResults){
            emails.add(studentTestResult.getStudentEmail());
        }

        // Obtener los resultados de cada estudiante
        List<StudentTestConfig> configs = studentTestConfigRepository.getAllByTestIdAndStudentEmailIn(testId, emails);

        Map<String, StudentTestConfig> configMap = new HashMap<>();
        for (StudentTestConfig config : configs) {
            configMap.put(config.getStudentEmail(), config);
        }

        // Asignar los resultados de cada estudiante
        for (StudentTestResult student : studentsResults) {
            StudentTestConfig config = configMap.get(student.getStudentEmail());

            if (config != null) {
                student.setFinalScore(config.getFinalScore() != null ? config.getFinalScore() : 0.0);
                student.setTotalAttemptsUsed(config.getTotalAttemptsUsed());
            } else {
                student.setFinalScore(0.0);
                student.setTotalAttemptsUsed(0);
            }
        }

        return studentsResults;
    }

    private Iterable<StudentTestResult> getTestResultsForGlobalPaged(int testId, int page, int size) {
        // Obtener la página de resultados
        Iterable<StudentTestResult> studentsResults = studentTestConfigRepository.getAllResultsByTestId(testId, page, size);

        if(!studentsResults.iterator().hasNext()){
            throw new NotFoundException("Ningún estudiante ha presentado esta evaluación");
        }

        Collection<String> emails = new ArrayList<>();
        for(StudentTestResult result : studentsResults){
            emails.add(result.getStudentEmail());
        }

        // Obtener los nombres y apellidos de los estudiantes
        List<StudentTestResult> studentsInfo = studentService.getStudentsByEmails(emails);

        Map<String, StudentTestResult> studentsInfoMap = new HashMap<>();
        for (StudentTestResult student : studentsInfo) {
            studentsInfoMap.put(student.getStudentEmail(), student);
        }

        // Asignar la información de cada estudiante
        long tempId = -1L;
        for(StudentTestResult student : studentsResults){
            StudentTestResult info = studentsInfoMap.get(student.getStudentEmail());

            if(info != null){
                student.setStudentId(info.getStudentId());
                student.setStudentFirstName(info.getStudentFirstName());
                student.setStudentLastName(info.getStudentLastName());
            }else{
                student.setStudentId(tempId--);
                student.setStudentFirstName("");
                student.setStudentLastName("");
            }
        }

        return studentsResults;
    }
}
