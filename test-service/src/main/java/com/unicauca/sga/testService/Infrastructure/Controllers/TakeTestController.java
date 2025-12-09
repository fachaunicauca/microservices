package com.unicauca.sga.testService.Infrastructure.Controllers;

import com.unicauca.sga.testService.Aplication.UseCases.TakeTestService;
import com.unicauca.sga.testService.Domain.Model.DTOs.QuestionListDTO;
import com.unicauca.sga.testService.Domain.Model.DTOs.StudentTestResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/takeTest")
@Tag(name="Controlador Presentar Test", description = "Funcionalidades necesarias para presentar el examen general del laboratorio.")
public class TakeTestController {

    private final TakeTestService takeTestService;

    public TakeTestController(TakeTestService takeTestService){
        this.takeTestService=takeTestService;
    }

    @Operation(summary = "Obtener los intentos restantes del estudiante",
                description = "Consulta los intentos restantes de un estudiante para realizar la evaluación.",
                responses={
                    @ApiResponse(responseCode = "200", description = "Cantidad de intentos obtenida con éxito."),
                    @ApiResponse(responseCode = "400", description = "Parámetros incorrectos o incompletos.")
                })
    @GetMapping("/getTries")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public short getTries(@RequestParam("actual_date")Date request_date,
                          @RequestParam("student_code") Long student_code){
        return takeTestService.getTries(request_date,student_code);
    }

    @Operation(summary = "Obtener las preguntas del Test",
            description = "Valida que el nombre del profesor, código de estudiante y materia estén "+
                            "registrados y retorna las preguntas del Test (20 preguntas).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Preguntas obtenidas con éxito."),
                    @ApiResponse(responseCode = "404", description = "Uno de los argumentos es invalido. Revisar el message."),
                    @ApiResponse(responseCode = "206", description = "La materia ingresada no tiene la cantidad de preguntas minima."),
                    @ApiResponse(responseCode = "204", description = "La materia ingresada no tiene preguntas.")
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public QuestionListDTO getTestQuestions(@RequestParam("subject_name") String subject_name,
                                            @RequestParam("student_code")Long student_code,
                                            @RequestParam("teacher_name") String teacher_name){
        return takeTestService.getTestQuestions(subject_name,student_code,teacher_name);
    }

    @Operation(summary = "Guardar y calificar test",
                description = "Recibe las preguntas respondidas por el estudiante, " +
                                "calcula la calificación y guarda los datos del test, sin almacenar las preguntas y respuestas. ",
                responses = {
                        @ApiResponse(responseCode = "200", description = "Test guardado y calificado exitosamente."),
                        @ApiResponse(responseCode = "404", description = "Uno de los datos recibidos no se encuentra registrado. Revisar el message."),
                })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public float saveAndScoreTest(@RequestBody StudentTestResponseDTO studentTestResponseDTO){
        return takeTestService.saveTest(studentTestResponseDTO);
    }

}
