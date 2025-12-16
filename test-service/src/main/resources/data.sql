INSERT INTO test (
    testId,
    teacherEmail,
    testTitle,
    testDescription,
    testDurationMinutes,
    testNumberOfQuestions,
    testAttemptLimit,
    testState,
    isPeriodic
) VALUES (
             1,
             'admin@unicauca.edu.co',
             'Evaluación General',
             'Evaluación destinada a medir los conocimientos básicos sobre normas de seguridad que deben poseer los estudiantes antes de ingresar a los laboratorios.',
             10,
             20,
             3,
             1,
             true
         );

INSERT INTO question (questionId, questionText, questionTitle, questionImage, testId) VALUES
  (1, '¿Cuál es la primera acción que debe tomar un estudiante al ingresar a un laboratorio de química?', NULL, NULL, 1),
  (2, '¿Qué debe hacer un estudiante si se derrama un reactivo químico en la piel?', NULL, NULL, 1),
  (3, '¿Qué se debe hacer con los desechos químicos generados en el laboratorio?', NULL, NULL, 1),
  (4, '¿Cuál es el propósito de las gafas de seguridad en un laboratorio de química?', NULL, NULL, 1),
  (5, '¿Qué debe hacer un estudiante si detecta un incendio en el laboratorio?', NULL, NULL, 1),
  (6, '¿Por qué es importante mantener el área de trabajo limpia en un laboratorio de química?', NULL, NULL, 1),
  (7, '¿Qué acción debe tomarse si una persona tiene una quemadura química?', NULL, NULL, 1),
  (8, '¿Dónde se deben almacenar los productos químicos en el laboratorio?', NULL, NULL, 1),
  (9, '¿Qué tipo de extintor se utiliza para fuegos causados por líquidos inflamables?', NULL, NULL, 1),
  (10, '¿Qué debe hacer un estudiante antes de comenzar a trabajar con reactivos químicos?', NULL, NULL, 1),
  (11, '¿Cómo se debe manejar un frasco de ácido en el laboratorio?', NULL, NULL, 1),
  (12, '¿Qué debe hacer un estudiante si se siente mareado o intoxicado en el laboratorio?', NULL, NULL, 1),
  (13, '¿Qué debe estar siempre disponible en un laboratorio de química?', NULL, NULL, 1),
  (14, '¿Qué se debe hacer con las sustancias químicas caducadas?', NULL, NULL, 1),
  (15, '¿Por qué es importante no comer ni beber en el laboratorio de química?', NULL, NULL, 1),
  (16, '¿Cómo se debe manipular un vaso medidor en el laboratorio?', NULL, NULL, 1),
  (17, '¿Qué equipo se debe utilizar para protegerse del riesgo de inhalación de vapores tóxicos?', NULL, NULL, 1),
  (18, '¿Dónde se encuentra el equipo de emergencia en un laboratorio?', NULL, NULL, 1),
  (19, '¿Qué precaución debe tomarse al trabajar con líquidos inflamables?', NULL, NULL, 1),
  (20, '¿Qué se debe hacer en caso de que un compañero de laboratorio sufra una quemadura?', NULL, NULL, 1),
  (21, '¿Cuál es el procedimiento correcto para diluir un ácido?', NULL, NULL, 1),
  (22, '¿Qué se debe hacer antes de encender un mechero Bunsen?', NULL, NULL, 1),
  (23, '¿Qué equipo se debe usar para calentar sustancias de forma segura?', NULL, NULL, 1),
  (24, '¿Cuál es la medida de seguridad más importante al manejar sustancias corrosivas?', NULL, NULL, 1),
  (25, '¿Qué debe hacer un estudiante al finalizar una práctica de laboratorio?', NULL, NULL, 1);

INSERT INTO question (questionId, questionText, questionTitle, questionImage, testId) VALUES
  (26, '¿Qué tipo de guantes se deben utilizar para manejar sustancias químicas peligrosas?', NULL, NULL, 1),
  (27, '¿Cómo se debe ventilar un laboratorio con productos químicos volátiles?', NULL, NULL, 1),
  (28, '¿Por qué es importante seguir los procedimientos de seguridad al trabajar con reacciones químicas?', NULL, NULL, 1),
  (29, '¿Qué debe hacer un estudiante si observa una fuga de gas en el laboratorio?', NULL, NULL, 1),
  (30, '¿Qué equipo de protección personal debe usarse al manipular productos corrosivos?', NULL, NULL, 1),
  (31, '¿Qué se debe hacer si se produce un derrame de un líquido tóxico en el laboratorio?', NULL, NULL, 1),
  (32, '¿Cuál de las siguientes es una acción correcta cuando se trabaja con sustancias altamente volátiles?', NULL, NULL, 1),
  (33, '¿Por qué se deben almacenar los productos químicos de manera separada según su clase?', NULL, NULL, 1),
  (34, '¿Qué debe hacer un estudiante si un frasco de reactivo se cae al suelo?', NULL, NULL, 1),
  (35, '¿Qué tipo de protección es necesaria cuando se trabaja con sustancias que pueden liberar gases peligrosos?', NULL, NULL, 1),
  (36, '¿Por qué es importante conocer la ficha de datos de seguridad (MSDS) de los productos químicos?', NULL, NULL, 1),
  (37, '¿Cuál es la forma correcta de manipular un cilindro de gas?', NULL, NULL, 1),
  (38, '¿Qué debe hacer un estudiante al terminar una práctica en el laboratorio de química?', NULL, NULL, 1),
  (39, '¿Qué se debe hacer si se detecta una fuga en un tubo de gas?', NULL, NULL, 1),
  (40, '¿Qué equipo es esencial para manipular reactivos sólidos de manera segura?', NULL, NULL, 1),
  (41, '¿Qué precaución debe tomarse cuando se trabaja con productos químicos que liberan vapores nocivos?', NULL, NULL, 1),
  (42, '¿Cómo debe proceder un estudiante si se rompe un frasco de vidrio con una sustancia peligrosa dentro?', NULL, NULL, 1),
  (43, '¿Por qué se deben utilizar frascos de vidrio adecuados para almacenar sustancias químicas?', NULL, NULL, 1),
  (44, '¿Qué debe hacer un estudiante si la campana de extracción de gases no está funcionando correctamente?', NULL, NULL, 1),
  (45, '¿Por qué es esencial mantener una adecuada ventilación en el laboratorio?', NULL, NULL, 1),
  (46, '¿Qué debe hacer un estudiante si entra en contacto con un producto químico irritante en los ojos?', NULL, NULL, 1),
  (47, '¿Cuál es la forma correcta de medir una cantidad de sólido en el laboratorio?', NULL, NULL, 1),
  (48, '¿Qué se debe hacer si se produce una reacción inesperada o peligrosa durante un experimento?', NULL, NULL, 1),
  (49, '¿Cómo se debe almacenar el mercurio en un laboratorio?', NULL, NULL, 1),
  (50, '¿Qué se debe hacer al utilizar una sustancia química que se encuentra en una botella sin etiqueta?', NULL, NULL, 1);

INSERT INTO singleChoiceQuestion (questionId) VALUES
  (1),(2),(3),(4),(5),
  (6),(7),(8),(9),(10),
  (11),(12),(13),(14),(15),
  (16),(17),(18),(19),(20),
  (21),(22),(23),(24),(25);

INSERT INTO singleChoiceQuestion (questionId) VALUES
  (26),(27),(28),(29),(30),
  (31),(32),(33),(34),(35),
  (36),(37),(38),(39),(40),
  (41),(42),(43),(44),(45),
  (46),(47),(48),(49),(50);


-- Pregunta 1
INSERT INTO answer (answerId, questionId) VALUES (1, 1);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (1, 'Leer las normas de seguridad del laboratorio.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (2, 1);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (2, 'Comenzar a mezclar reactivos.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (3, 1);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (3, 'Encender el mechero Bunsen.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (4, 1);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (4, 'Tomar asiento sin hacer nada.', FALSE);

-- Pregunta 2
INSERT INTO answer (answerId, questionId) VALUES (5, 2);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (5, 'Lavar la zona afectada con abundante agua.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (6, 2);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (6, 'Cubrir la piel sin lavar la zona.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (7, 2);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (7, 'Aplicar alcohol directamente.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (8, 2);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (8, 'Ignorar el incidente.', FALSE);

-- Pregunta 3
INSERT INTO answer (answerId, questionId) VALUES (9, 3);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (9, 'Desecharlos en los contenedores adecuados.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (10, 3);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (10, 'Verterlos en el fregadero.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (11, 3);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (11, 'Tirarlos a la basura común.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (12, 3);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (12, 'Guardarlos para uso futuro.', FALSE);

-- Pregunta 4
INSERT INTO answer (answerId, questionId) VALUES (13, 4);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (13, 'Proteger los ojos de salpicaduras y vapores.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (14, 4);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (14, 'Mejorar la visión en el laboratorio.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (15, 4);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (15, 'Proteger la cara del calor.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (16, 4);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (16, 'Evitar la fatiga visual.', FALSE);

-- Pregunta 5
INSERT INTO answer (answerId, questionId) VALUES (17, 5);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (17, 'Activar la alarma y evacuar el laboratorio.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (18, 5);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (18, 'Intentar apagar el fuego sin protección.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (19, 5);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (19, 'Ignorar el fuego.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (20, 5);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (20, 'Esperar instrucciones sin moverse.', FALSE);

-- Pregunta 6
INSERT INTO answer (answerId, questionId) VALUES (21, 6);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (21, 'Evitar accidentes y contaminaciones cruzadas.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (22, 6);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (22, 'Mejorar la estética del laboratorio.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (23, 6);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (23, 'Ahorrar tiempo en la limpieza final.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (24, 6);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (24, 'Impresionar al profesor.', FALSE);

-- Pregunta 7
INSERT INTO answer (answerId, questionId) VALUES (25, 7);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (25, 'Lavar la zona con abundante agua durante al menos 15 minutos.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (26, 7);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (26, 'Cubrir la quemadura con un paño seco.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (27, 7);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (27, 'Aplicar ungüento directamente.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (28, 7);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (28, 'No hacer nada y seguir trabajando.', FALSE);

-- Pregunta 8
INSERT INTO answer (answerId, questionId) VALUES (29, 8);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (29, 'En armarios ventilados y etiquetados correctamente.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (30, 8);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (30, 'En cualquier lugar del laboratorio.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (31, 8);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (31, 'Cerca de fuentes de calor.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (32, 8);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (32, 'Junto a alimentos.', FALSE);

-- Pregunta 9
INSERT INTO answer (answerId, questionId) VALUES (33, 9);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (33, 'Extintor tipo B.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (34, 9);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (34, 'Extintor tipo A.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (35, 9);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (35, 'Extintor tipo C.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (36, 9);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (36, 'Extintor tipo D.', FALSE);

-- Pregunta 10
INSERT INTO answer (answerId, questionId) VALUES (37, 10);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (37, 'Leer las etiquetas y fichas de seguridad de los reactivos.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (38, 10);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (38, 'Oler el reactivo directamente.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (39, 10);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (39, 'Probar el reactivo para identificarlo.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (40, 10);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (40, 'Mezclar los reactivos al azar.', FALSE);

-- Pregunta 11
INSERT INTO answer (answerId, questionId) VALUES (41, 11);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (41, 'Con guantes y gafas de seguridad, y transportarlo con ambas manos.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (42, 11);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (42, 'Sin protección y usando una sola mano.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (43, 11);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (43, 'Apoyándolo sobre el borde de la mesa.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (44, 11);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (44, 'Sacudiéndolo antes de abrirlo.', FALSE);

-- Pregunta 12
INSERT INTO answer (answerId, questionId) VALUES (45, 12);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (45, 'Avisar al profesor y salir a tomar aire fresco.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (46, 12);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (46, 'Seguir trabajando hasta desmayarse.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (47, 12);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (47, 'Tomar algún reactivo para recuperarse.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (48, 12);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (48, 'Ignorar la situación.', FALSE);

-- Pregunta 13
INSERT INTO answer (answerId, questionId) VALUES (49, 13);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (49, 'Equipo de primeros auxilios.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (50, 13);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (50, 'Comida y bebida.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (51, 13);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (51, 'Libros de lectura.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (52, 13);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (52, 'Juegos de mesa.', FALSE);

-- Pregunta 14
INSERT INTO answer (answerId, questionId) VALUES (53, 14);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (53, 'Desecharlas siguiendo los protocolos de seguridad.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (54, 14);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (54, 'Mezclarlas con nuevas sustancias.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (55, 14);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (55, 'Guardarlas en cualquier lugar.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (56, 14);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (56, 'Darlas a los estudiantes para practicar.', FALSE);

-- Pregunta 15
INSERT INTO answer (answerId, questionId) VALUES (57, 15);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (57, 'Para evitar contaminación química y riesgos para la salud.', TRUE);

INSERT INTO answer (answerId, questionId) VALUES (58, 15);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (58, 'Porque es una regla sin sentido.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (59, 15);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (59, 'Porque no hay tiempo para comer.', FALSE);

INSERT INTO answer (answerId, questionId) VALUES (60, 15);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect)
VALUES (60, 'Para evitar manchas en los informes.', FALSE);

-- Respuestas para la pregunta 16
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (61, 16);
INSERT INTO answer (answerId, questionId) VALUES (62, 16);
INSERT INTO answer (answerId, questionId) VALUES (63, 16);
INSERT INTO answer (answerId, questionId) VALUES (64, 16);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (61, 'Sosteniéndolo firmemente con ambas manos y nivelando a la vista.', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (62, 'Agitándolo mientras se mide.', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (63, 'Colocándolo sobre una superficie inclinada.', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (64, 'Vertiendo líquidos a la fuerza.', FALSE);

-- Respuestas para la pregunta 17
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (65, 17);
INSERT INTO answer (answerId, questionId) VALUES (66, 17);
INSERT INTO answer (answerId, questionId) VALUES (67, 17);
INSERT INTO answer (answerId, questionId) VALUES (68, 17);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (65, 'Mascarilla o campana extractora.', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (66, 'Guantes de látex solamente.', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (67, 'Gorro de baño.', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (68, 'Ningún equipo es necesario.', FALSE);

-- Respuestas para la pregunta 18
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (69, 18);
INSERT INTO answer (answerId, questionId) VALUES (70, 18);
INSERT INTO answer (answerId, questionId) VALUES (71, 18);
INSERT INTO answer (answerId, questionId) VALUES (72, 18);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (69, 'En un lugar visible y de fácil acceso.', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (70, 'Guardado bajo llave.', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (71, 'En el almacén general.', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (72, 'En la oficina del director.', FALSE);

-- Respuestas para la pregunta 19
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (73, 19);
INSERT INTO answer (answerId, questionId) VALUES (74, 19);
INSERT INTO answer (answerId, questionId) VALUES (75, 19);
INSERT INTO answer (answerId, questionId) VALUES (76, 19);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (73, 'Mantenerlos alejados de fuentes de ignición y usar protección adecuada.', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (74, 'Calentarlos directamente sobre fuego.', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (75, 'Almacenarlos en recipientes abiertos.', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (76, 'Transportarlos sin precauciones.', FALSE);

-- Respuestas para la pregunta 20
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (77, 20);
INSERT INTO answer (answerId, questionId) VALUES (78, 20);
INSERT INTO answer (answerId, questionId) VALUES (79, 20);
INSERT INTO answer (answerId, questionId) VALUES (80, 20);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (77, 'Aplicar agua fría sobre la quemadura y avisar al profesor.', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (78, 'Cubrir la quemadura con una manta.', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (79, 'Ponerle hielo directamente.', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (80, 'Seguir trabajando sin atenderlo.', FALSE);

-- Respuestas para la pregunta 21
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (81, 21);
INSERT INTO answer (answerId, questionId) VALUES (82, 21);
INSERT INTO answer (answerId, questionId) VALUES (83, 21);
INSERT INTO answer (answerId, questionId) VALUES (84, 21);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (81, 'Una bata de algodón normal', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (82, 'Una bata de laboratorio resistente a productos químicos', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (83, 'Una camiseta vieja y pantalones largos', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (84, 'Cualquier ropa que cubra la piel', FALSE);

-- Respuestas para la pregunta 22
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (85, 22);
INSERT INTO answer (answerId, questionId) VALUES (86, 22);
INSERT INTO answer (answerId, questionId) VALUES (87, 22);
INSERT INTO answer (answerId, questionId) VALUES (88, 22);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (85, 'En lugares ventilados, lejos de fuentes de calor', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (86, 'En cualquier lugar de la sala', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (87, 'Junto con los productos inflamables', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (88, 'No es necesario almacenarlo de forma especial', FALSE);

-- Respuestas para la pregunta 23
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (89, 23);
INSERT INTO answer (answerId, questionId) VALUES (90, 23);
INSERT INTO answer (answerId, questionId) VALUES (91, 23);
INSERT INTO answer (answerId, questionId) VALUES (92, 23);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (89, 'Usarlo sin preocupación', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (90, 'Informar al profesor y no usarlo', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (91, 'Descartarlo en el agua corriente', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (92, 'No hacer nada', FALSE);

-- Respuestas para la pregunta 24
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (93, 24);
INSERT INTO answer (answerId, questionId) VALUES (94, 24);
INSERT INTO answer (answerId, questionId) VALUES (95, 24);
INSERT INTO answer (answerId, questionId) VALUES (96, 24);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (93, 'Mirar la lectura desde arriba', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (94, 'Leer la medición a la altura del menisco del líquido', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (95, 'Verificar la medición desde un lado', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (96, 'Aproximarse al volumen deseado sin ser exacto', FALSE);

-- Respuestas para la pregunta 25
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (97, 25);
INSERT INTO answer (answerId, questionId) VALUES (98, 25);
INSERT INTO answer (answerId, questionId) VALUES (99, 25);
INSERT INTO answer (answerId, questionId) VALUES (100, 25);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (97, 'Ignorarlo y seguir trabajando', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (98, 'Limpiar los fragmentos con las manos', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (99, 'Usar un barrido adecuado y desecharlo en el recipiente de vidrio roto', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (100, 'Tirarlo al cubo de basura común', FALSE);

-- Respuestas para la pregunta 26
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (101, 26);
INSERT INTO answer (answerId, questionId) VALUES (102, 26);
INSERT INTO answer (answerId, questionId) VALUES (103, 26);
INSERT INTO answer (answerId, questionId) VALUES (104, 26);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (101, 'Guantes de tela', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (102, 'Guantes de goma o látex adecuados para sustancias químicas', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (103, 'Guantes de cocina', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (104, 'No es necesario usar guantes', FALSE);

-- Respuestas para la pregunta 27
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (105, 27);
INSERT INTO answer (answerId, questionId) VALUES (106, 27);
INSERT INTO answer (answerId, questionId) VALUES (107, 27);
INSERT INTO answer (answerId, questionId) VALUES (108, 27);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (105, 'Usando una campana de extracción de gases', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (106, 'Abriendo las ventanas', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (107, 'Apagando los sistemas de calefacción', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (108, 'No es necesario ventilar', FALSE);

-- Respuestas para la pregunta 28
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (109, 28);
INSERT INTO answer (answerId, questionId) VALUES (110, 28);
INSERT INTO answer (answerId, questionId) VALUES (111, 28);
INSERT INTO answer (answerId, questionId) VALUES (112, 28);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (109, 'Para obtener resultados más rápidos', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (110, 'Para evitar accidentes y mantener un ambiente seguro', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (111, 'Para evitar que los productos cambien de color', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (112, 'Porque es una regla de la universidad', FALSE);

-- Respuestas para la pregunta 29
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (113, 29);
INSERT INTO answer (answerId, questionId) VALUES (114, 29);
INSERT INTO answer (answerId, questionId) VALUES (115, 29);
INSERT INTO answer (answerId, questionId) VALUES (116, 29);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (113, 'Ignorarla', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (114, 'Apagar todas las luces y equipos eléctricos', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (115, 'Informar inmediatamente al profesor y evacuar el área', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (116, 'Intentar sellar la fuga con cinta', FALSE);

-- Respuestas para la pregunta 30
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (117, 30);
INSERT INTO answer (answerId, questionId) VALUES (118, 30);
INSERT INTO answer (answerId, questionId) VALUES (119, 30);
INSERT INTO answer (answerId, questionId) VALUES (120, 30);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (117, 'Solo guantes', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (118, 'Bata de laboratorio y gafas de seguridad', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (119, 'Solo gafas de seguridad', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (120, 'Ningún equipo es necesario', FALSE);

-- Respuestas para la pregunta 31
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (121, 31);
INSERT INTO answer (answerId, questionId) VALUES (122, 31);
INSERT INTO answer (answerId, questionId) VALUES (123, 31);
INSERT INTO answer (answerId, questionId) VALUES (124, 31);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (121, 'Ignorarlo si el derrame es pequeño', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (122, 'Limpiar el derrame inmediatamente con un trapo seco', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (123, 'Avisar al profesor, evacuar el área y limpiar el derrame con materiales adecuados', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (124, 'Tirar el líquido al inodoro', FALSE);

-- Respuestas para la pregunta 32
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (125, 32);
INSERT INTO answer (answerId, questionId) VALUES (126, 32);
INSERT INTO answer (answerId, questionId) VALUES (127, 32);
INSERT INTO answer (answerId, questionId) VALUES (128, 32);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (125, 'Trabajar bajo una campana de extracción de gases', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (126, 'Abrir las ventanas del laboratorio para mejorar la ventilación', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (127, 'Usar solo guantes sin máscara', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (128, 'No es necesario tomar precauciones', FALSE);

-- Respuestas para la pregunta 33
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (129, 33);
INSERT INTO answer (answerId, questionId) VALUES (130, 33);
INSERT INTO answer (answerId, questionId) VALUES (131, 33);
INSERT INTO answer (answerId, questionId) VALUES (132, 33);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (129, 'Para ahorrar espacio', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (130, 'Para evitar reacciones peligrosas en caso de derrames', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (131, 'Para facilitar su identificación', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (132, 'No es necesario almacenarlos separados', FALSE);

-- Respuestas para la pregunta 34
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (133, 34);
INSERT INTO answer (answerId, questionId) VALUES (134, 34);
INSERT INTO answer (answerId, questionId) VALUES (135, 34);
INSERT INTO answer (answerId, questionId) VALUES (136, 34);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (133, 'Recoger los fragmentos con las manos', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (134, 'Limpiar el área de inmediato sin precauciones', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (135, 'Avisar al profesor y seguir las instrucciones del protocolo de limpieza', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (136, 'No hacer nada y continuar trabajando', FALSE);

-- Respuestas para la pregunta 35
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (137, 35);
INSERT INTO answer (answerId, questionId) VALUES (138, 35);
INSERT INTO answer (answerId, questionId) VALUES (139, 35);
INSERT INTO answer (answerId, questionId) VALUES (140, 35);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (137, 'Solo gafas de seguridad', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (138, 'Máscara con filtro adecuado y guantes', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (139, 'Solo guantes', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (140, 'Ninguna protección es necesaria', FALSE);

-- Respuestas para la pregunta 36
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (141, 36);
INSERT INTO answer (answerId, questionId) VALUES (142, 36);
INSERT INTO answer (answerId, questionId) VALUES (143, 36);
INSERT INTO answer (answerId, questionId) VALUES (144, 36);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (141, 'Para conocer sus aplicaciones', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (142, 'Para saber cómo desecharlos', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (143, 'Para conocer los riesgos y las medidas de emergencia en caso de accidente', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (144, 'Porque es obligatorio', FALSE);

-- Respuestas para la pregunta 37
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (145, 37);
INSERT INTO answer (answerId, questionId) VALUES (146, 37);
INSERT INTO answer (answerId, questionId) VALUES (147, 37);
INSERT INTO answer (answerId, questionId) VALUES (148, 37);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (145, 'Sostenerlo solo con las manos', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (146, 'Utilizar un soporte adecuado y asegurarlo correctamente', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (147, 'Golpearlo ligeramente para asegurarse de que no tiene fugas', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (148, 'No importa cómo se manipule mientras se use', FALSE);

-- Respuestas para la pregunta 38
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (149, 38);
INSERT INTO answer (answerId, questionId) VALUES (150, 38);
INSERT INTO answer (answerId, questionId) VALUES (151, 38);
INSERT INTO answer (answerId, questionId) VALUES (152, 38);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (149, 'Limpiar su área de trabajo y almacenar los reactivos de manera adecuada', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (150, 'Dejar todo tal como está para que lo limpien otros', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (151, 'Solo cerrar los frascos de los reactivos', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (152, 'Dejar todo el material en el lugar para que el siguiente grupo lo use', FALSE);

-- Respuestas para la pregunta 39
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (153, 39);
INSERT INTO answer (answerId, questionId) VALUES (154, 39);
INSERT INTO answer (answerId, questionId) VALUES (155, 39);
INSERT INTO answer (answerId, questionId) VALUES (156, 39);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (153, 'Informar de inmediato al profesor y cerrar la válvula de gas', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (154, 'Intentar arreglar la fuga sin avisar', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (155, 'Salir corriendo del laboratorio', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (156, 'Dejar que la fuga pase desapercibida', FALSE);

-- Respuestas para la pregunta 40
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (157, 40);
INSERT INTO answer (answerId, questionId) VALUES (158, 40);
INSERT INTO answer (answerId, questionId) VALUES (159, 40);
INSERT INTO answer (answerId, questionId) VALUES (160, 40);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (157, 'Ningún equipo es necesario', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (158, 'Solo guantes', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (159, 'Guantes, gafas de seguridad y bata', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (160, 'Máscara y guantes', FALSE);

-- Respuestas para la pregunta 41
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (161, 41);
INSERT INTO answer (answerId, questionId) VALUES (162, 41);
INSERT INTO answer (answerId, questionId) VALUES (163, 41);
INSERT INTO answer (answerId, questionId) VALUES (164, 41);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (161, 'Utilizar una máscara respiratoria adecuada', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (162, 'Trabajar solo en espacios cerrados', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (163, 'Evitar leer las hojas de seguridad', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (164, 'Trabajar sin precauciones adicionales', FALSE);

-- Respuestas para la pregunta 42
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (165, 42);
INSERT INTO answer (answerId, questionId) VALUES (166, 42);
INSERT INTO answer (answerId, questionId) VALUES (167, 42);
INSERT INTO answer (answerId, questionId) VALUES (168, 42);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (165, 'Recoger los fragmentos con las manos desnudas', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (166, 'Avisar al profesor y limpiar con el equipo adecuado', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (167, 'Tirar los fragmentos en la basura común', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (168, 'Ignorar el incidente', FALSE);

-- Respuestas para la pregunta 43
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (169, 43);
INSERT INTO answer (answerId, questionId) VALUES (170, 43);
INSERT INTO answer (answerId, questionId) VALUES (171, 43);
INSERT INTO answer (answerId, questionId) VALUES (172, 43);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (169, 'Porque el vidrio es barato', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (170, 'Porque el vidrio es resistente a los productos químicos', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (171, 'Porque se pueden almacenar grandes cantidades de químicos', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (172, 'Porque el vidrio no es necesario', FALSE);

-- Respuestas para la pregunta 44
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (173, 44);
INSERT INTO answer (answerId, questionId) VALUES (174, 44);
INSERT INTO answer (answerId, questionId) VALUES (175, 44);
INSERT INTO answer (answerId, questionId) VALUES (176, 44);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (173, 'Continuar trabajando sin preocuparse', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (174, 'Avisar al profesor y detener el trabajo', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (175, 'Utilizar otro espacio del laboratorio', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (176, 'Dejar la campana funcionando sin tomar precauciones', FALSE);

-- Respuestas para la pregunta 45
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (177, 45);
INSERT INTO answer (answerId, questionId) VALUES (178, 45);
INSERT INTO answer (answerId, questionId) VALUES (179, 45);
INSERT INTO answer (answerId, questionId) VALUES (180, 45);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (177, 'Para que los estudiantes no se aburran', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (178, 'Para evitar la acumulación de vapores tóxicos', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (179, 'Para hacer que el laboratorio se vea más limpio', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (180, 'Porque no se permite trabajar sin ventilación', FALSE);

-- Respuestas para la pregunta 46
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (181, 46);
INSERT INTO answer (answerId, questionId) VALUES (182, 46);
INSERT INTO answer (answerId, questionId) VALUES (183, 46);
INSERT INTO answer (answerId, questionId) VALUES (184, 46);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (181, 'Llamar a su profesor y esperar', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (182, 'Lavar inmediatamente los ojos con agua abundante', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (183, 'Aplicar un tratamiento sin consultar a nadie', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (184, 'Ignorar el incidente', FALSE);

-- Respuestas para la pregunta 47
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (185, 47);
INSERT INTO answer (answerId, questionId) VALUES (186, 47);
INSERT INTO answer (answerId, questionId) VALUES (187, 47);
INSERT INTO answer (answerId, questionId) VALUES (188, 47);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (185, 'Usando una cuchara de cocina', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (186, 'Usando una balanza adecuada para el laboratorio', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (187, 'Usando las manos directamente', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (188, 'No es necesario medir los sólidos', FALSE);

-- Respuestas para la pregunta 48
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (189, 48);
INSERT INTO answer (answerId, questionId) VALUES (190, 48);
INSERT INTO answer (answerId, questionId) VALUES (191, 48);
INSERT INTO answer (answerId, questionId) VALUES (192, 48);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (189, 'Ignorar la reacción y continuar trabajando', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (190, 'Avisar al profesor inmediatamente y seguir los procedimientos de emergencia', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (191, 'Tratar de controlar la reacción con las manos', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (192, 'Salir corriendo del laboratorio', FALSE);

-- Respuestas para la pregunta 49
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (193, 49);
INSERT INTO answer (answerId, questionId) VALUES (194, 49);
INSERT INTO answer (answerId, questionId) VALUES (195, 49);
INSERT INTO answer (answerId, questionId) VALUES (196, 49);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (193, 'En un frasco común', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (194, 'En un frasco de vidrio cerrado y etiquetado adecuadamente', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (195, 'No se debe almacenar', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (196, 'En el mismo lugar que los demás líquidos', FALSE);

-- Respuestas para la pregunta 50
-- Base AnswerTable
INSERT INTO answer (answerId, questionId) VALUES (197, 50);
INSERT INTO answer (answerId, questionId) VALUES (198, 50);
INSERT INTO answer (answerId, questionId) VALUES (199, 50);
INSERT INTO answer (answerId, questionId) VALUES (200, 50);
-- ChoiceAnswerTable
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (197, 'Utilizarla sin problema', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (198, 'Tirarla al inodoro', FALSE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (199, 'Consultar al profesor para que identifique la sustancia antes de usarla', TRUE);
INSERT INTO choiceAnswer (answerId, answerText, isCorrect) VALUES (200, 'Ignorar que no tiene etiqueta', FALSE);