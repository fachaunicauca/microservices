-- Inserciones tabla question_topic
INSERT INTO question_topic (qt_description) VALUES ('TOPIC DEFAULT');

-- Inserciones tabla "subject"
INSERT INTO "subject" (subject_name)
VALUES ('Materia 4'),
       ('Materia 3'),
       ('Materia 2'),
       ('Materia 1');

-- Inserciones tabla question
INSERT INTO question (question_id, question_topic_id, subject_name, question_text) VALUES
(1, 1, 'Materia 1', '¿Cuál es la primera acción que debe tomar un estudiante al ingresar a un laboratorio de química?'),
(2, 1, 'Materia 1', '¿Qué debe hacer un estudiante si se derrama un reactivo químico en la piel?'),
(3, 1, 'Materia 1', '¿Qué se debe hacer con los desechos químicos generados en el laboratorio?'),
(4, 1, 'Materia 1', '¿Cuál es el propósito de las gafas de seguridad en un laboratorio de química?'),
(5, 1, 'Materia 1', '¿Qué debe hacer un estudiante si detecta un incendio en el laboratorio?'),
(6, 1, 'Materia 1', '¿Por qué es importante mantener el área de trabajo limpia en un laboratorio de química?'),
(7, 1, 'Materia 1', '¿Qué acción debe tomarse si una persona tiene una quemadura química?'),
(8, 1, 'Materia 1', '¿Dónde se deben almacenar los productos químicos en el laboratorio?'),
(9, 1, 'Materia 1', '¿Qué tipo de extintor se utiliza para fuegos causados por líquidos inflamables?'),
(10, 1, 'Materia 1', '¿Qué debe hacer un estudiante antes de comenzar a trabajar con reactivos químicos?'),
(11, 1, 'Materia 1', '¿Cómo se debe manejar un frasco de ácido en el laboratorio?'),
(12, 1, 'Materia 1', '¿Qué debe hacer un estudiante si se siente mareado o intoxicado en el laboratorio?'),
(13, 1, 'Materia 1', '¿Qué debe estar siempre disponible en un laboratorio de química?'),
(14, 1, 'Materia 1', '¿Qué se debe hacer con las sustancias químicas caducadas?'),
(15, 1, 'Materia 1', '¿Por qué es importante no comer ni beber en el laboratorio de química?'),
(16, 1, 'Materia 1', '¿Cómo se debe manipular un vaso medidor en el laboratorio?'),
(17, 1, 'Materia 1', '¿Qué equipo se debe utilizar para protegerse del riesgo de inhalación de vapores tóxicos?'),
(18, 1, 'Materia 1', '¿Dónde se encuentra el equipo de emergencia en un laboratorio?'),
(19, 1, 'Materia 1', '¿Qué precaución debe tomarse al trabajar con líquidos inflamables?'),
(20, 1, 'Materia 1', '¿Qué se debe hacer en caso de que un compañero de laboratorio sufra una quemadura?'),
(21, 1, 'Materia 1', '¿Cuál es el procedimiento correcto para diluir un ácido?'),
(22, 1, 'Materia 1', '¿Qué se debe hacer antes de encender un mechero Bunsen?'),
(23, 1, 'Materia 1', '¿Qué equipo se debe usar para calentar sustancias de forma segura?'),
(24, 1, 'Materia 1', '¿Cuál es la medida de seguridad más importante al manejar sustancias corrosivas?'),
(25, 1, 'Materia 1', '¿Qué debe hacer un estudiante al finalizar una práctica de laboratorio?');

INSERT INTO question (question_id, question_topic_id, subject_name, question_text) VALUES
(26, 1, 'Materia 2', '¿Qué tipo de guantes se deben utilizar para manejar sustancias químicas peligrosas?'),
(27, 1, 'Materia 2', '¿Cómo se debe ventilar un laboratorio con productos químicos volátiles?'),
(28, 1, 'Materia 2', '¿Por qué es importante seguir los procedimientos de seguridad al trabajar con reacciones químicas?'),
(29, 1, 'Materia 2', '¿Qué debe hacer un estudiante si observa una fuga de gas en el laboratorio?'),
(30, 1, 'Materia 2', '¿Qué equipo de protección personal debe usarse al manipular productos corrosivos?'),
(31, 1, 'Materia 2', '¿Qué se debe hacer si se produce un derrame de un líquido tóxico en el laboratorio?'),
(32, 1, 'Materia 2', '¿Cuál de las siguientes es una acción correcta cuando se trabaja con sustancias altamente volátiles?'),
(33, 1, 'Materia 2', '¿Por qué se deben almacenar los productos químicos de manera separada según su clase?'),
(34, 1, 'Materia 2', '¿Qué debe hacer un estudiante si un frasco de reactivo se cae al suelo?'),
(35, 1, 'Materia 2', '¿Qué tipo de protección es necesaria cuando se trabaja con sustancias que pueden liberar gases peligrosos?'),
(36, 1, 'Materia 2', '¿Por qué es importante conocer la ficha de datos de seguridad (MSDS) de los productos químicos?'),
(37, 1, 'Materia 2', '¿Cuál es la forma correcta de manipular un cilindro de gas?'),
(38, 1, 'Materia 2', '¿Qué debe hacer un estudiante al terminar una práctica en el laboratorio de química?'),
(39, 1, 'Materia 2', '¿Qué se debe hacer si se detecta una fuga en un tubo de gas?'),
(40, 1, 'Materia 2', '¿Qué equipo es esencial para manipular reactivos sólidos de manera segura?'),
(41, 1, 'Materia 2', '¿Qué precaución debe tomarse cuando se trabaja con productos químicos que liberan vapores nocivos?'),
(42, 1, 'Materia 2', '¿Cómo debe proceder un estudiante si se rompe un frasco de vidrio con una sustancia peligrosa dentro?'),
(43, 1, 'Materia 2', '¿Por qué se deben utilizar frascos de vidrio adecuados para almacenar sustancias químicas?'),
(44, 1, 'Materia 2', '¿Qué debe hacer un estudiante si la campana de extracción de gases no está funcionando correctamente?'),
(45, 1, 'Materia 2', '¿Por qué es esencial mantener una adecuada ventilación en el laboratorio?'),
(46, 1, 'Materia 2', '¿Qué debe hacer un estudiante si entra en contacto con un producto químico irritante en los ojos?'),
(47, 1, 'Materia 2', '¿Cuál es la forma correcta de medir una cantidad de sólido en el laboratorio?'),
(48, 1, 'Materia 3', '¿Qué se debe hacer si se produce una reacción inesperada o peligrosa durante un experimento?'),
(49, 1, 'Materia 3', '¿Cómo se debe almacenar el mercurio en un laboratorio?'),
(50, 1, 'Materia 3', '¿Qué se debe hacer al utilizar una sustancia química que se encuentra en una botella sin etiqueta?');

-- Inserciones tabla answer
-- Respuestas para la pregunta 1
-- Respuestas para la pregunta 1
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (1, 1, 'Leer las normas de seguridad del laboratorio.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (1, 2, 'Comenzar a mezclar reactivos.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (1, 3, 'Encender el mechero Bunsen.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (1, 4, 'Tomar asiento sin hacer nada.', FALSE);

-- Respuestas para la pregunta 2
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (2, 5, 'Lavar la zona afectada con abundante agua.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (2, 6, 'Cubrir la piel sin lavar la zona.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (2, 7, 'Aplicar alcohol directamente.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (2, 8, 'Ignorar el incidente.', FALSE);

-- Respuestas para la pregunta 3
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (3, 9, 'Desecharlos en los contenedores adecuados.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (3, 10, 'Verterlos en el fregadero.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (3, 11, 'Tirarlos a la basura común.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (3, 12, 'Guardarlos para uso futuro.', FALSE);

-- Respuestas para la pregunta 4
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (4, 13, 'Proteger los ojos de salpicaduras y vapores.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (4, 14, 'Mejorar la visión en el laboratorio.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (4, 15, 'Proteger la cara del calor.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (4, 16, 'Evitar la fatiga visual.', FALSE);

-- Respuestas para la pregunta 5
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (5, 17, 'Activar la alarma y evacuar el laboratorio.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (5, 18, 'Intentar apagar el fuego sin protección.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (5, 19, 'Ignorar el fuego.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (5, 20, 'Esperar instrucciones sin moverse.', FALSE);

-- Respuestas para la pregunta 6
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (6, 21, 'Evitar accidentes y contaminaciones cruzadas.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (6, 22, 'Mejorar la estética del laboratorio.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (6, 23, 'Ahorrar tiempo en la limpieza final.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (6, 24, 'Impresionar al profesor.', FALSE);

-- Respuestas para la pregunta 7
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (7, 25, 'Lavar la zona con abundante agua durante al menos 15 minutos.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (7, 26, 'Cubrir la quemadura con un paño seco.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (7, 27, 'Aplicar ungüento directamente.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (7, 28, 'No hacer nada y seguir trabajando.', FALSE);

-- Respuestas para la pregunta 8
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (8, 29, 'En armarios ventilados y etiquetados correctamente.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (8, 30, 'En cualquier lugar del laboratorio.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (8, 31, 'Cerca de fuentes de calor.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (8, 32, 'Junto a alimentos.', FALSE);

-- Respuestas para la pregunta 9
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (9, 33, 'Extintor tipo B.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (9, 34, 'Extintor tipo A.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (9, 35, 'Extintor tipo C.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (9, 36, 'Extintor tipo D.', FALSE);

-- Respuestas para la pregunta 10
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (10, 37, 'Leer las etiquetas y fichas de seguridad de los reactivos.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (10, 38, 'Oler el reactivo directamente.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (10, 39, 'Probar el reactivo para identificarlo.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (10, 40, 'Mezclar los reactivos al azar.', FALSE);

-- Respuestas para la pregunta 11
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (11, 41, 'Con guantes y gafas de seguridad, y transportarlo con ambas manos.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (11, 42, 'Sin protección y usando una sola mano.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (11, 43, 'Apoyándolo sobre el borde de la mesa.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (11, 44, 'Sacudiéndolo antes de abrirlo.', FALSE);

-- Respuestas para la pregunta 12
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (12, 45, 'Avisar al profesor y salir a tomar aire fresco.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (12, 46, 'Seguir trabajando hasta desmayarse.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (12, 47, 'Tomar algún reactivo para recuperarse.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (12, 48, 'Ignorar la situación.', FALSE);

-- Respuestas para la pregunta 13
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (13, 49, 'Equipo de primeros auxilios.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (13, 50, 'Comida y bebida.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (13, 51, 'Libros de lectura.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (13, 52, 'Juegos de mesa.', FALSE);

-- Respuestas para la pregunta 14
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (14, 53, 'Desecharlas siguiendo los protocolos de seguridad.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (14, 54, 'Mezclarlas con nuevas sustancias.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (14, 55, 'Guardarlas en cualquier lugar.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (14, 56, 'Darlas a los estudiantes para practicar.', FALSE);

-- Respuestas para la pregunta 15
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (15, 57, 'Para evitar contaminación química y riesgos para la salud.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (15, 58, 'Porque es una regla sin sentido.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (15, 59, 'Porque no hay tiempo para comer.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (15, 60, 'Para evitar manchas en los informes.', FALSE);

-- Respuestas para la pregunta 16
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (16, 61, 'Sosteniéndolo firmemente con ambas manos y nivelando a la vista.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (16, 62, 'Agitándolo mientras se mide.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (16, 63, 'Colocándolo sobre una superficie inclinada.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (16, 64, 'Vertiendo líquidos a la fuerza.', FALSE);

-- Respuestas para la pregunta 17
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (17, 65, 'Mascarilla o campana extractora.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (17, 66, 'Guantes de látex solamente.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (17, 67, 'Gorro de baño.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (17, 68, 'Ningún equipo es necesario.', FALSE);

-- Respuestas para la pregunta 18
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (18, 69, 'En un lugar visible y de fácil acceso.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (18, 70, 'Guardado bajo llave.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (18, 71, 'En el almacén general.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (18, 72, 'En la oficina del director.', FALSE);

-- Respuestas para la pregunta 19
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (19, 73, 'Mantenerlos alejados de fuentes de ignición y usar protección adecuada.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (19, 74, 'Calentarlos directamente sobre fuego.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (19, 75, 'Almacenarlos en recipientes abiertos.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (19, 76, 'Transportarlos sin precauciones.', FALSE);

-- Respuestas para la pregunta 20
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (20, 77, 'Aplicar agua fría sobre la quemadura y avisar al profesor.', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (20, 78, 'Cubrir la quemadura con una manta.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (20, 79, 'Ponerle hielo directamente.', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (20, 80, 'Seguir trabajando sin atenderlo.', FALSE);

-- Respuestas para la pregunta 21
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (21, 81, 'Una bata de algodón normal', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (21, 82, 'Una bata de laboratorio resistente a productos químicos', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (21, 83, 'Una camiseta vieja y pantalones largos', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (21, 84, 'Cualquier ropa que cubra la piel', FALSE);

-- Respuestas para la pregunta 22
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (22, 85, 'En lugares ventilados, lejos de fuentes de calor', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (22, 86, 'En cualquier lugar de la sala', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (22, 87, 'Junto con los productos inflamables', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (22, 88, 'No es necesario almacenarlo de forma especial', FALSE);

-- Respuestas para la pregunta 23
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (23, 89, 'Usarlo sin preocupación', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (23, 90, 'Informar al profesor y no usarlo', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (23, 91, 'Descartarlo en el agua corriente', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (23, 92, 'No hacer nada', FALSE);

-- Respuestas para la pregunta 24
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (24, 93, 'Mirar la lectura desde arriba', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (24, 94, 'Leer la medición a la altura del menisco del líquido', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (24, 95, 'Verificar la medición desde un lado', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (24, 96, 'Aproximarse al volumen deseado sin ser exacto', FALSE);

-- Respuestas para la pregunta 25
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (25, 97, 'Ignorarlo y seguir trabajando', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (25, 98, 'Limpiar los fragmentos con las manos', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (25, 99, 'Usar un barrido adecuado y desecharlo en el recipiente de vidrio roto', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (25, 100, 'Tirarlo al cubo de basura común', FALSE);

-- Respuestas para la pregunta 26
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (26, 101, 'Guantes de tela', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (26, 102, 'Guantes de goma o látex adecuados para sustancias químicas', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (26, 103, 'Guantes de cocina', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (26, 104, 'No es necesario usar guantes', FALSE);

-- Respuestas para la pregunta 27
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (27, 105, 'Usando una campana de extracción de gases', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (27, 106, 'Abriendo las ventanas', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (27, 107, 'Apagando los sistemas de calefacción', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (27, 108, 'No es necesario ventilar', FALSE);

-- Respuestas para la pregunta 28
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (28, 109, 'Para obtener resultados más rápidos', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (28, 110, 'Para evitar accidentes y mantener un ambiente seguro', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (28, 111, 'Para evitar que los productos cambien de color', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (28, 112, 'Porque es una regla de la universidad', FALSE);

-- Respuestas para la pregunta 29
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (29, 113, 'Ignorarla', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (29, 114, 'Apagar todas las luces y equipos eléctricos', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (29, 115, 'Informar inmediatamente al profesor y evacuar el área', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (29, 116, 'Intentar sellar la fuga con cinta', FALSE);

-- Respuestas para la pregunta 30
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (30, 117, 'Solo guantes', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (30, 118, 'Bata de laboratorio y gafas de seguridad', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (30, 119, 'Solo gafas de seguridad', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (30, 120, 'Ningún equipo es necesario', FALSE);

-- Respuestas para la pregunta 31
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (31, 121, 'Ignorarlo si el derrame es pequeño', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (31, 122, 'Limpiar el derrame inmediatamente con un trapo seco', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (31, 123, 'Avisar al profesor, evacuar el área y limpiar el derrame con materiales adecuados', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (31, 124, 'Tirar el líquido al inodoro', FALSE);

-- Respuestas para la pregunta 32
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (32, 125, 'Trabajar bajo una campana de extracción de gases', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (32, 126, 'Abrir las ventanas del laboratorio para mejorar la ventilación', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (32, 127, 'Usar solo guantes sin máscara', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (32, 128, 'No es necesario tomar precauciones', FALSE);

-- Respuestas para la pregunta 33
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (33, 129, 'Para ahorrar espacio', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (33, 130, 'Para evitar reacciones peligrosas en caso de derrames', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (33, 131, 'Para facilitar su identificación', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (33, 132, 'No es necesario almacenarlos separados', FALSE);

-- Respuestas para la pregunta 34
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (34, 133, 'Recoger los fragmentos con las manos', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (34, 134, 'Limpiar el área de inmediato sin precauciones', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (34, 135, 'Avisar al profesor y seguir las instrucciones del protocolo de limpieza', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (34, 136, 'No hacer nada y continuar trabajando', FALSE);
-- Respuestas para la pregunta 35
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (35, 137, 'Solo gafas de seguridad', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (35, 138, 'Máscara con filtro adecuado y guantes', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (35, 139, 'Solo guantes', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (35, 140, 'Ninguna protección es necesaria', FALSE);

-- Respuestas para la pregunta 36
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (36, 141, 'Para conocer sus aplicaciones', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (36, 142, 'Para saber cómo desecharlos', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (36, 143, 'Para conocer los riesgos y las medidas de emergencia en caso de accidente', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (36, 144, 'Porque es obligatorio', FALSE);

-- Respuestas para la pregunta 37
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (37, 145, 'Sostenerlo solo con las manos', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (37, 146, 'Utilizar un soporte adecuado y asegurarlo correctamente', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (37, 147, 'Golpearlo ligeramente para asegurarse de que no tiene fugas', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (37, 148, 'No importa cómo se manipule mientras se use', FALSE);

-- Respuestas para la pregunta 38
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (38, 149, 'Limpiar su área de trabajo y almacenar los reactivos de manera adecuada', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (38, 150, 'Dejar todo tal como está para que lo limpien otros', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (38, 151, 'Solo cerrar los frascos de los reactivos', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (38, 152, 'Dejar todo el material en el lugar para que el siguiente grupo lo use', FALSE);

-- Respuestas para la pregunta 39
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (39, 153, 'Informar de inmediato al profesor y cerrar la válvula de gas', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (39, 154, 'Intentar arreglar la fuga sin avisar', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (39, 155, 'Salir corriendo del laboratorio', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (39, 156, 'Dejar que la fuga pase desapercibida', FALSE);

-- Respuestas para la pregunta 40
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (40, 157, 'Ningún equipo es necesario', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (40, 158, 'Solo guantes', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (40, 159, 'Guantes, gafas de seguridad y bata', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (40, 160, 'Máscara y guantes', FALSE);

-- Respuestas para la pregunta 41
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (41, 161, 'Utilizar una máscara respiratoria adecuada', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (41, 162, 'Trabajar solo en espacios cerrados', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (41, 163, 'Evitar leer las hojas de seguridad', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (41, 164, 'Trabajar sin precauciones adicionales', FALSE);

-- Respuestas para la pregunta 42
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (42, 165, 'Recoger los fragmentos con las manos desnudas', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (42, 166, 'Avisar al profesor y limpiar con el equipo adecuado', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (42, 167, 'Tirar los fragmentos en la basura común', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (42, 168, 'Ignorar el incidente', FALSE);

-- Respuestas para la pregunta 43
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (43, 169, 'Porque el vidrio es barato', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (43, 170, 'Porque el vidrio es resistente a los productos químicos', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (43, 171, 'Porque se pueden almacenar grandes cantidades de químicos', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (43, 172, 'Porque el vidrio no es necesario', FALSE);

-- Respuestas para la pregunta 44
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (44, 173, 'Continuar trabajando sin preocuparse', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (44, 174, 'Avisar al profesor y detener el trabajo', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (44, 175, 'Utilizar otro espacio del laboratorio', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (44, 176, 'Dejar la campana funcionando sin tomar precauciones', FALSE);

-- Respuestas para la pregunta 45
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (45, 177, 'Para que los estudiantes no se aburran', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (45, 178, 'Para evitar la acumulación de vapores tóxicos', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (45, 179, 'Para hacer que el laboratorio se vea más limpio', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (45, 180, 'Porque no se permite trabajar sin ventilación', FALSE);

-- Respuestas para la pregunta 46
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (46, 181, 'Llamar a su profesor y esperar', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (46, 182, 'Lavar inmediatamente los ojos con agua abundante', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (46, 183, 'Aplicar un tratamiento sin consultar a nadie', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (46, 184, 'Ignorar el incidente', FALSE);

-- Respuestas para la pregunta 47
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (47, 185, 'Usando una cuchara de cocina', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (47, 186, 'Usando una balanza adecuada para el laboratorio', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (47, 187, 'Usando las manos directamente', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (47, 188, 'No es necesario medir los sólidos', FALSE);

-- Respuestas para la pregunta 48
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (48, 189, 'Ignorar la reacción y continuar trabajando', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (48, 190, 'Avisar al profesor inmediatamente y seguir los procedimientos de emergencia', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (48, 191, 'Tratar de controlar la reacción con las manos', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (48, 192, 'Salir corriendo del laboratorio', FALSE);

-- Respuestas para la pregunta 49
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (49, 193, 'En un frasco común', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (49, 194, 'En un frasco de vidrio cerrado y etiquetado adecuadamente', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (49, 195, 'No se debe almacenar', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (49, 196, 'En el mismo lugar que los demás líquidos', FALSE);

-- Respuestas para la pregunta 50
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (50, 197, 'Utilizarla sin problema', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (50, 198, 'Tirarla al inodoro', FALSE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (50, 199, 'Consultar al profesor para que identifique la sustancia antes de usarla', TRUE);
INSERT INTO answer (question_id, answer_id, answer_text, answer_is_correct)
VALUES (50, 200, 'Ignorar que no tiene etiqueta', FALSE);


-- Estudiante 101 presenta 3 veces
INSERT INTO test (teacher_id, teacher_name, student_id, num_of_questions, subject_name, test_date, test_score)
VALUES (1, 'Julio Hurtado', 101, 20, 'Materia 1', '2025-02-01', 0.50);

INSERT INTO test (teacher_id, teacher_name, student_id, num_of_questions, subject_name, test_date, test_score)
VALUES (1, 'Julio Hurtado', 101, 20, 'Materia 1', '2025-03-15', 0.48);

INSERT INTO test (teacher_id, teacher_name, student_id, num_of_questions, subject_name, test_date, test_score)
VALUES (1, 'Julio Hurtado', 101, 20, 'Materia 1', '2025-05-10', 0.45);

-- Estudiante 102 presenta 2 veces
INSERT INTO test (teacher_id, teacher_name, student_id, num_of_questions, subject_name, test_date, test_score)
VALUES (2, 'Claudia Muñoz', 102, 25, 'Materia 1', '2025-03-05', 0.50);

INSERT INTO test (teacher_id, teacher_name, student_id, num_of_questions, subject_name, test_date, test_score)
VALUES (2, 'Claudia Muñoz', 102, 25, 'Materia 1', '2025-06-01', 0.47);

-- Estudiante 103 presenta 1 vez
INSERT INTO test (teacher_id, teacher_name, student_id, num_of_questions, subject_name, test_date, test_score)
VALUES (3, 'Carlos Pérez', 103, 30, 'Materia 1', '2025-04-20', 0.49);

-- Estudiante 104 presenta 2 veces y la 2da aprobó el examen
INSERT INTO test (teacher_id, teacher_name, student_id, num_of_questions, subject_name, test_date, test_score)
VALUES (3, 'Perdomo', 104, 30, 'Materia 1', '2025-04-20', 0.48);

INSERT INTO test (teacher_id, teacher_name, student_id, num_of_questions, subject_name, test_date, test_score)
VALUES (3, 'Perdomo', 104, 30, 'Materia 1', '2025-04-20', 0.78);





