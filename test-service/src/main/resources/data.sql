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

INSERT INTO question (questionId, questionText, questionTitle, questionImage, testId, questionType, questionStructure) VALUES
    (1, '¿Cuál es la primera acción que debe tomar un estudiante al ingresar a un laboratorio de química?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Encender el mechero Bunsen.","correct":false},{"id":2,"text":"Leer las normas de seguridad del laboratorio.","correct":true},{"id":3,"text":"Comenzar a mezclar reactivos.","correct":false},{"id":4,"text":"Tomar asiento sin hacer nada.","correct":false}],"correctAnswerCount":1}'),

    (2, '¿Qué debe hacer un estudiante si se derrama un reactivo químico en la piel?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Cubrir la piel sin lavar la zona.","correct":false},{"id":2,"text":"Aplicar alcohol directamente.","correct":false},{"id":3,"text":"Ignorar el incidente.","correct":false},{"id":4,"text":"Lavar la zona afectada con abundante agua.","correct":true}],"correctAnswerCount":1}'),

    (3, '¿Qué se debe hacer con los desechos químicos generados en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Desecharlos en los contenedores adecuados.","correct":true},{"id":2,"text":"Verterlos en el fregadero.","correct":false},{"id":3,"text":"Tirarlos a la basura común.","correct":false},{"id":4,"text":"Guardarlos para uso futuro.","correct":false}],"correctAnswerCount":1}'),

    (4, '¿Cuál es el propósito de las gafas de seguridad en un laboratorio de química?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Mejorar la visión en el laboratorio.","correct":false},{"id":2,"text":"Proteger los ojos de salpicaduras y vapores.","correct":true},{"id":3,"text":"Proteger la cara del calor.","correct":false},{"id":4,"text":"Evitar la fatiga visual.","correct":false}],"correctAnswerCount":1}'),

    (5, '¿Qué debe hacer un estudiante si detecta un incendio en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Intentar apagar el fuego sin protección.","correct":false},{"id":2,"text":"Ignorar el fuego.","correct":false},{"id":3,"text":"Activar la alarma y evacuar el laboratorio.","correct":true},{"id":4,"text":"Esperar instrucciones sin moverse.","correct":false}],"correctAnswerCount":1}'),

    (6, '¿Por qué es importante mantener el área de trabajo limpia en un laboratorio de química?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Evitar accidentes y contaminaciones cruzadas.","correct":true},{"id":2,"text":"Mejorar la estética del laboratorio.","correct":false},{"id":3,"text":"Ahorrar tiempo en la limpieza final.","correct":false},{"id":4,"text":"Impresionar al profesor.","correct":false}],"correctAnswerCount":1}'),

    (7, '¿Qué acción debe tomarse si una persona tiene una quemadura química?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Cubrir la quemadura con un paño seco.","correct":false},{"id":2,"text":"Lavar la zona con abundante agua durante al menos 15 minutos.","correct":true},{"id":3,"text":"Aplicar ungüento directamente.","correct":false},{"id":4,"text":"No hacer nada y seguir trabajando.","correct":false}],"correctAnswerCount":1}'),

    (8, '¿Dónde se deben almacenar los productos químicos en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"En cualquier lugar del laboratorio.","correct":false},{"id":2,"text":"Cerca de fuentes de calor.","correct":false},{"id":3,"text":"En armarios ventilados y etiquetados correctamente.","correct":true},{"id":4,"text":"Junto a alimentos.","correct":false}],"correctAnswerCount":1}'),

    (9, '¿Qué tipo de extintor se utiliza para fuegos causados por líquidos inflamables?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Extintor tipo A.","correct":false},{"id":2,"text":"Extintor tipo B.","correct":true},{"id":3,"text":"Extintor tipo C.","correct":false},{"id":4,"text":"Extintor tipo D.","correct":false}],"correctAnswerCount":1}'),

    (10, '¿Qué debe hacer un estudiante antes de comenzar a trabajar con reactivos químicos?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
'{"answers":[{"id":1,"text":"Oler el reactivo directamente.","correct":false},{"id":2,"text":"Probar el reactivo para identificarlo.","correct":false},{"id":3,"text":"Mezclar los reactivos al azar.","correct":false},{"id":4,"text":"Leer las etiquetas y fichas de seguridad de los reactivos.","correct":true}],"correctAnswerCount":1}');

INSERT INTO question (questionId, questionText, questionTitle, questionImage, testId, questionType, questionStructure) VALUES
    (11, '¿Cómo se debe manejar un frasco de ácido en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Sin protección y usando una sola mano.","correct":false},{"id":2,"text":"Con guantes y gafas de seguridad, y transportarlo con ambas manos.","correct":true},{"id":3,"text":"Apoyándolo sobre el borde de la mesa.","correct":false},{"id":4,"text":"Sacudiéndolo antes de abrirlo.","correct":false}],"correctAnswerCount":1}'),

    (12, '¿Qué debe hacer un estudiante si se siente mareado o intoxicado en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Seguir trabajando hasta desmayarse.","correct":false},{"id":2,"text":"Tomar algún reactivo para recuperarse.","correct":false},{"id":3,"text":"Avisar al profesor y salir a tomar aire fresco.","correct":true},{"id":4,"text":"Ignorar la situación.","correct":false}],"correctAnswerCount":1}'),

    (13, '¿Qué debe estar siempre disponible en un laboratorio de química?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Comida y bebida.","correct":false},{"id":2,"text":"Equipo de primeros auxilios.","correct":true},{"id":3,"text":"Libros de lectura.","correct":false},{"id":4,"text":"Juegos de mesa.","correct":false}],"correctAnswerCount":1}'),

    (14, '¿Qué se debe hacer con las sustancias químicas caducadas?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Desecharlas siguiendo los protocolos de seguridad.","correct":true},{"id":2,"text":"Mezclarlas con nuevas sustancias.","correct":false},{"id":3,"text":"Guardarlas en cualquier lugar.","correct":false},{"id":4,"text":"Darlas a los estudiantes para practicar.","correct":false}],"correctAnswerCount":1}'),

    (15, '¿Por qué es importante no comer ni beber en el laboratorio de química?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Porque es una regla sin sentido.","correct":false},{"id":2,"text":"Porque no hay tiempo para comer.","correct":false},{"id":3,"text":"Para evitar contaminación química y riesgos para la salud.","correct":true},{"id":4,"text":"Para evitar manchas en los informes.","correct":false}],"correctAnswerCount":1}'),

    (16, '¿Cómo se debe manipular un vaso medidor en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Agitándolo mientras se mide.","correct":false},{"id":2,"text":"Sosteniéndolo firmemente con ambas manos y nivelando a la vista.","correct":true},{"id":3,"text":"Colocándolo sobre una superficie inclinada.","correct":false},{"id":4,"text":"Vertiendo líquidos a la fuerza.","correct":false}],"correctAnswerCount":1}'),

    (17, '¿Qué equipo se debe utilizar para protegerse del riesgo de inhalación de vapores tóxicos?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Mascarilla o campana extractora.","correct":true},{"id":2,"text":"Guantes de látex solamente.","correct":false},{"id":3,"text":"Gorro de baño.","correct":false},{"id":4,"text":"Ningún equipo es necesario.","correct":false}],"correctAnswerCount":1}'),

    (18, '¿Dónde se encuentra el equipo de emergencia en un laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Guardado bajo llave.","correct":false},{"id":2,"text":"En el almacén general.","correct":false},{"id":3,"text":"En la oficina del director.","correct":false},{"id":4,"text":"En un lugar visible y de fácil acceso.","correct":true}],"correctAnswerCount":1}'),

    (19, '¿Qué precaución debe tomarse al trabajar con líquidos inflamables?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Mantenerlos alejados de fuentes de ignición y usar protección adecuada.","correct":true},{"id":2,"text":"Calentarlos directamente sobre fuego.","correct":false},{"id":3,"text":"Almacenarlos en recipientes abiertos.","correct":false},{"id":4,"text":"Transportarlos sin precauciones.","correct":false}],"correctAnswerCount":1}'),

    (20, '¿Qué se debe hacer en caso de que un compañero de laboratorio sufra una quemadura?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Cubrir la quemadura con una manta.","correct":false},{"id":2,"text":"Aplicar agua fría sobre la quemadura y avisar al profesor.","correct":true},{"id":3,"text":"Ponerle hielo directamente.","correct":false},{"id":4,"text":"Seguir trabajando sin atenderlo.","correct":false}],"correctAnswerCount":1}'),

    (21, '¿Cuál es el procedimiento correcto para diluir un ácido?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Añadir agua sobre el ácido rápidamente.","correct":false},{"id":2,"text":"Mezclarlos ambos al mismo tiempo.","correct":false},{"id":3,"text":"Añadir siempre el ácido sobre el agua lentamente.","correct":true},{"id":4,"text":"No importa el orden de mezcla.","correct":false}],"correctAnswerCount":1}'),

    (22, '¿Qué se debe hacer antes de encender un mechero Bunsen?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Asegurarse de que no haya fugas de gas y retirar inflamables.","correct":true},{"id":2,"text":"Abrir el gas al máximo antes de encender.","correct":false},{"id":3,"text":"Encenderlo cerca de reactivos volátiles.","correct":false},{"id":4,"text":"No es necesaria ninguna revisión.","correct":false}],"correctAnswerCount":1}'),

    (23, '¿Qué equipo se debe usar para calentar sustancias de forma segura?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Sostener el tubo con las manos.","correct":false},{"id":2,"text":"Baño María, placa calefactora o mechero con trípode y rejilla.","correct":true},{"id":3,"text":"Usar un encendedor común.","correct":false},{"id":4,"text":"Cualquier recipiente de plástico.","correct":false}],"correctAnswerCount":1}'),

    (24, '¿Cuál es la medida de seguridad más importante al manejar sustancias corrosivas?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Manejarlo con rapidez para evitar contacto.","correct":false},{"id":2,"text":"Olerlo para verificar su concentración.","correct":false},{"id":3,"text":"Uso de guantes de nitrilo, bata y protección ocular.","correct":true},{"id":4,"text":"Usar ropa de calle normal.","correct":false}],"correctAnswerCount":1}'),

    (25, '¿Qué debe hacer un estudiante al finalizar una práctica de laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
'{"answers":[{"id":1,"text":"Salir inmediatamente sin limpiar.","correct":false},{"id":2,"text":"Limpiar el material, ordenar el puesto y lavarse las manos.","correct":true},{"id":3,"text":"Dejar los reactivos abiertos para el siguiente grupo.","correct":false},{"id":4,"text":"Tirar todo a la basura común.","correct":false}],"correctAnswerCount":1}');

INSERT INTO question (questionId, questionText, questionTitle, questionImage, testId, questionType, questionStructure) VALUES
   (26, '¿Cómo se debe actuar ante la presencia de un gas desconocido en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Inhalar profundamente para identificarlo.","correct":false},{"id":2,"text":"Evacuar el área y ventilar el laboratorio.","correct":true},{"id":3,"text":"Ignorar el olor si no es muy fuerte.","correct":false},{"id":4,"text":"Cerrar todas las ventanas.","correct":false}],"correctAnswerCount":1}'),

   (27, '¿Qué se debe hacer si se rompe un termómetro de mercurio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Recoger el mercurio con las manos.","correct":false},{"id":2,"text":"Limpiarlo con una fregona común.","correct":false},{"id":3,"text":"Informar al profesor para su recogida especial con kit de derrames.","correct":true},{"id":4,"text":"Echarle agua caliente encima.","correct":false}],"correctAnswerCount":1}'),

   (28, '¿Qué característica debe tener el calzado para trabajar en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Sandalias abiertas para mayor ventilación.","correct":false},{"id":2,"text":"Cualquier tipo de calzado deportivo.","correct":false},{"id":3,"text":"Zapatos cerrados que cubran todo el pie.","correct":true},{"id":4,"text":"Zapatos con tacón alto.","correct":false}],"correctAnswerCount":1}'),

   (29, '¿Cuál es la función de la ducha de seguridad?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Refrescarse en días de calor.","correct":false},{"id":2,"text":"Lavar el material de vidrio grande.","correct":false},{"id":3,"text":"Eliminar rápidamente contaminantes químicos del cuerpo en caso de derrame.","correct":true},{"id":4,"text":"Limpiar el piso del laboratorio.","correct":false}],"correctAnswerCount":1}'),

   (30, '¿Cómo se debe pipetear una solución química?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Succionando directamente con la boca.","correct":false},{"id":2,"text":"Utilizando una propipeta o pera de succión.","correct":true},{"id":3,"text":"Vertiendo el líquido con cuidado.","correct":false},{"id":4,"text":"Usando un pitillo común.","correct":false}],"correctAnswerCount":1}'),

   (31, '¿Qué indica el pictograma de una llama sobre un círculo?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Sustancia inflamable.","correct":false},{"id":2,"text":"Sustancia comburente (favorece el incendio).","correct":true},{"id":3,"text":"Sustancia explosiva.","correct":false},{"id":4,"text":"Sustancia corrosiva.","correct":false}],"correctAnswerCount":1}'),

   (32, '¿Qué se debe hacer con el cabello largo en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Llevarlo suelto para mayor comodidad.","correct":false},{"id":2,"text":"Mantenerlo siempre recogido.","correct":true},{"id":3,"text":"Cubrirlo con una gorra de lado.","correct":false},{"id":4,"text":"No requiere ninguna medida especial.","correct":false}],"correctAnswerCount":1}'),

   (33, '¿Cómo debe ser el etiquetado de un frasco con una solución preparada?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Nombre de la sustancia, concentración, fecha y responsable.","correct":true},{"id":2,"text":"Solo el nombre de la sustancia.","correct":false},{"id":3,"text":"No es necesario etiquetar si se usa el mismo día.","correct":false},{"id":4,"text":"Basta con una marca de color.","correct":false}],"correctAnswerCount":1}'),

   (34, '¿Qué riesgo principal representa el uso de lentes de contacto en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Pueden atrapar vapores químicos entre el lente y el ojo.","correct":true},{"id":2,"text":"Se pueden caer fácilmente.","correct":false},{"id":3,"text":"No permiten ver bien a través de las gafas de seguridad.","correct":false},{"id":4,"text":"Hacen que el ojo se seque más rápido.","correct":false}],"correctAnswerCount":1}'),

   (35, '¿Qué se debe hacer si una probeta de vidrio se agrieta?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Ponerle cinta adhesiva y seguir usándola.","correct":false},{"id":2,"text":"Desecharla inmediatamente en el contenedor de vidrio roto.","correct":true},{"id":3,"text":"Guardarla para medir sustancias no peligrosas.","correct":false},{"id":4,"text":"Ignorar la grieta si no gotea.","correct":false}],"correctAnswerCount":1}'),

   (36, '¿Cuál es la forma correcta de calentar un tubo de ensayo a la llama?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Mirando directamente al interior del tubo.","correct":false},{"id":2,"text":"Apuntando la boca del tubo hacia uno mismo.","correct":false},{"id":3,"text":"Inclinado y apuntando la boca hacia un lugar vacío, moviéndolo suavemente.","correct":true},{"id":4,"text":"Manteniéndolo fijo en la parte más caliente de la llama.","correct":false}],"correctAnswerCount":1}'),

   (37, '¿Qué significa el pictograma de una calavera y tibias cruzadas?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Peligro de muerte por toxicidad aguda.","correct":true},{"id":2,"text":"Sustancia irritante.","correct":false},{"id":3,"text":"Peligro para el medio ambiente.","correct":false},{"id":4,"text":"Sustancia radioactiva.","correct":false}],"correctAnswerCount":1}'),

   (38, '¿Qué debe hacerse si el gas de un mechero no enciende tras varios intentos?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Seguir intentándolo hasta que funcione.","correct":false},{"id":2,"text":"Cerrar la llave del gas y esperar a que se disipe antes de volver a intentar.","correct":true},{"id":3,"text":"Soplar el mechero.","correct":false},{"id":4,"text":"Pedirle a un compañero que use su mechero cerca.","correct":false}],"correctAnswerCount":1}'),

   (39, '¿Cómo se deben transportar los tubos de ensayo?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"En la mano todos juntos.","correct":false},{"id":2,"text":"En los bolsillos de la bata.","correct":false},{"id":3,"text":"En una gradilla.","correct":true},{"id":4,"text":"Dentro de un vaso de precipitados.","correct":false}],"correctAnswerCount":1}'),

   (40, '¿Qué se debe hacer antes de usar un equipo eléctrico en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Conectarlo directamente sin revisar.","correct":false},{"id":2,"text":"Verificar que los cables no estén pelados y que el área esté seca.","correct":true},{"id":3,"text":"Limpiarlo con un paño húmedo mientras está encendido.","correct":false},{"id":4,"text":"Usarlo solo si es estrictamente necesario.","correct":false}],"correctAnswerCount":1}');

INSERT INTO question (questionId, questionText, questionTitle, questionImage, testId, questionType, questionStructure) VALUES
   (41, '¿Qué se debe hacer si se detecta una fuga de gas en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Abrir todas las ventanas y cerrar la llave de paso principal.","correct":true},{"id":2,"text":"Encender un mechero para ver dónde está la fuga.","correct":false},{"id":3,"text":"Seguir trabajando con cuidado.","correct":false},{"id":4,"text":"Cerrar las puertas y ventanas para que el gas no salga.","correct":false}],"correctAnswerCount":1}'),

   (42, '¿Cuál es la forma segura de oler una sustancia química en el laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Acercar la nariz directamente al frasco.","correct":false},{"id":2,"text":"Inhalar profundamente el vapor.","correct":false},{"id":3,"text":"Abanicar suavemente el vapor hacia la nariz con la mano.","correct":true},{"id":4,"text":"No se deben oler las sustancias bajo ninguna circunstancia.","correct":false}],"correctAnswerCount":1}'),

   (43, '¿Qué se debe hacer con los recipientes vacíos que contenían reactivos peligrosos?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Tirarlos a la basura de oficina.","correct":false},{"id":2,"text":"Lavarlos y usarlos para beber agua.","correct":false},{"id":3,"text":"Tratarlos como residuos peligrosos según el protocolo.","correct":true},{"id":4,"text":"Dejarlos en el fregadero.","correct":false}],"correctAnswerCount":1}'),

   (44, '¿Qué significa el pictograma de un signo de exclamación en un rombo rojo?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Sustancia altamente explosiva.","correct":false},{"id":2,"text":"Atención: irritante cutáneo, toxicidad aguda o peligro para la capa de ozono.","correct":true},{"id":3,"text":"Peligro biológico.","correct":false},{"id":4,"text":"Sustancia inflamable.","correct":false}],"correctAnswerCount":1}'),

   (45, '¿Cómo se debe actuar si una sustancia química entra en contacto con los ojos?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Frotarse los ojos con fuerza.","correct":false},{"id":2,"text":"Esperar a que pase el ardor.","correct":false},{"id":3,"text":"Usar el lavaojos durante al menos 15-20 minutos manteniendo los ojos abiertos.","correct":true},{"id":4,"text":"Cerrar los ojos y vendar.","correct":false}],"correctAnswerCount":1}'),

   (46, '¿Cuál es la función principal de la bata de laboratorio?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Hacer que el estudiante parezca profesional.","correct":false},{"id":2,"text":"Proteger la piel y la ropa de salpicaduras y derrames químicos.","correct":true},{"id":3,"text":"Mantener el cuerpo caliente.","correct":false},{"id":4,"text":"Identificar quién es estudiante y quién es profesor.","correct":false}],"correctAnswerCount":1}'),

   (47, '¿Qué precaución se debe tener con las placas calefactoras encendidas?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"No tocarlas y asegurarse de que los cables no toquen la superficie caliente.","correct":true},{"id":2,"text":"Dejarlas encendidas al salir del laboratorio.","correct":false},{"id":3,"text":"Enfriarlas rápidamente con agua fría.","correct":false},{"id":4,"text":"Tocarlas rápidamente para ver si ya están calientes.","correct":false}],"correctAnswerCount":1}'),

   (48, '¿Qué debe hacerse con los guantes desechables después de su uso?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Lavarlos y reutilizarlos en la siguiente práctica.","correct":false},{"id":2,"text":"Retirarlos sin tocar la parte externa y desecharlos en el contenedor adecuado.","correct":true},{"id":3,"text":"Tirarlos en cualquier papelera.","correct":false},{"id":4,"text":"Dejarlos sobre la mesa de trabajo.","correct":false}],"correctAnswerCount":1}'),

   (49, '¿Cómo se debe almacenar una solución volátil?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"En un frasco común abierto.","correct":false},{"id":2,"text":"En un frasco de vidrio cerrado, etiquetado y en un área ventilada.","correct":true},{"id":3,"text":"No se debe almacenar nunca.","correct":false},{"id":4,"text":"En el mismo lugar que los líquidos inflamables sin tapa.","correct":false}],"correctAnswerCount":1}'),

   (50, '¿Qué debe hacer si encuentra un material de vidrio con un borde astillado?', NULL, NULL, 1, 'MULTIPLE_CHOICE',
    '{"answers":[{"id":1,"text":"Utilizarlo con mucho cuidado.","correct":false},{"id":2,"text":"No utilizarlo y entregarlo al responsable para su descarte o reparación.","correct":true},{"id":3,"text":"Limar el borde con una lija.","correct":false},{"id":4,"text":"Ignorarlo si el daño es pequeño.","correct":false}],"correctAnswerCount":1}');