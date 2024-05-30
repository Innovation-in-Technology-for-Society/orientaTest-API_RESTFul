package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.TestPreguntaMapper;
import com.its.orientaTest.model.dto.TestPreguntaRequestDTO;
import com.its.orientaTest.model.dto.TestPreguntaResponseDTO;
import com.its.orientaTest.model.entities.Carrera;
import com.its.orientaTest.model.entities.CarreraUniversidad;
import com.its.orientaTest.model.entities.Pregunta;
import com.its.orientaTest.model.entities.Resultado;
import com.its.orientaTest.model.entities.Test;
import com.its.orientaTest.model.entities.TestPregunta;
import com.its.orientaTest.model.entities.Universidad;
import com.its.orientaTest.repository.CarreraRepository;
import com.its.orientaTest.repository.CarreraUniversidadRepository;
import com.its.orientaTest.repository.PreguntaRepository;
import com.its.orientaTest.repository.ResultadoRepository;
import com.its.orientaTest.repository.TestPreguntaRepository;
import com.its.orientaTest.repository.TestRepository;
import com.its.orientaTest.repository.UniversidadRepository;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TestPreguntaService {
    private final TestPreguntaMapper testPreguntaMapper;
    private final TestRepository testRepository;
    private final PreguntaRepository preguntaRepository;
    private final TestPreguntaRepository testPreguntaRepository;
    private final CarreraUniversidadRepository carreraUniversidadRepository;
    private final CarreraRepository carreraRepository;
    private final UniversidadRepository universidadRepository;
    private final ResultadoRepository resultadoRepository;

    @Transactional 
    public void getPreguntasVocacionales(Long test_id){
        List<Pregunta> preguntas = preguntaRepository.findVocacional();
        Collections.shuffle(preguntas);
        preguntas = preguntas.subList(0, Math.min(preguntas.size(), 25));
        savePreguntas(test_id,  preguntas, "vocacional");
    }

    @Transactional 
    public void getPreguntasAutoPercepcion(Long test_id){
        List<Pregunta> preguntas = preguntaRepository.findAutoPercepcion();
        Collections.shuffle(preguntas);
        preguntas = preguntas.subList(0, Math.min(preguntas.size(), 10));
        savePreguntas(test_id,  preguntas, "auto-percepcion");
    }
    @Transactional(readOnly = true)
    public List<TestPreguntaResponseDTO> getResultadosTipoTest(Long testId, String tipoTest) {
        List<TestPregunta> preguntas = testPreguntaRepository.findByTestIdAndTipoTest(testId, tipoTest);
        return preguntas.stream()
                .map(testPreguntaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public void savePreguntas(Long test_id, List<Pregunta> preguntas, String tipo_test){
        for (Pregunta pregunta : preguntas){

            // Verificar que el test se realizó 
            Test test = testRepository.findById(test_id)
            .orElseThrow(() -> new ResourceNotFoundException("Test no encontrado con id: " + test_id));

            TestPregunta testPregunta = new TestPregunta();
            testPregunta.setTest(test);
            testPregunta.setPregunta(pregunta);
            testPregunta.setTipoTest(tipo_test);
            testPregunta.setValor(0);
            testPregunta = testPreguntaRepository.save(testPregunta);
        }
    }
    
    @Transactional
    public TestPreguntaResponseDTO answerPregunta(Long test_id, Long id, TestPreguntaRequestDTO testPreguntaRequestDTO){
        Test test = testRepository.findById(test_id)
        .orElseThrow(() -> new ResourceNotFoundException("No existe el Test con id " + test_id));

        TestPregunta testPregunta = testPreguntaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No existe la pregunta"));

        testPregunta.setTest(test);
        testPregunta.setValor(testPreguntaRequestDTO.getValor());
        testPregunta = testPreguntaRepository.save(testPregunta);

        return testPreguntaMapper.toDTO(testPregunta);
    }
      
    private final List<Long> tags_vocacional = List.of(1L, 2L, 3L);
    private final Map<String, List<Long>> tags_autopercepcion = Map.of(
        "Universidad Tecnológica del Perú", List.of(5L, 6L, 7L, 10L, 13L),
        "Universidad Peruana Cayetano Heredia", List.of(5L, 6L, 9L, 10L, 13L),
        "Universidad Peruana de Ciencias Aplicadas", List.of(4L, 5L, 10L, 12L, 13L)
    );

    @Transactional
    public void calculateResultado(Long test_id){
        Map<String, Integer> puntaje_universidad = new HashMap<>();
        Map<String, Integer> puntaje_carrera = new HashMap<>();

        List<TestPregunta> preguntas = testPreguntaRepository.findByTestId(test_id);

        for (TestPregunta pregunta : preguntas){
            if(pregunta.getValor().equals(1)){
                Long categoria_id = pregunta.getPregunta().getCategoria().getId();
                for (String universidad : tags_autopercepcion.keySet()) {
                    if (tags_autopercepcion.get(universidad).contains(categoria_id)) {
                        puntaje_universidad.put(universidad, puntaje_universidad.getOrDefault(universidad, 0) + 1);
                    }
                }
                if (tags_vocacional.contains(categoria_id)) {
                    String carreraNombre = pregunta.getPregunta().getCategoria().getNombre();
                    puntaje_carrera.put(carreraNombre, puntaje_carrera.getOrDefault(carreraNombre, 0) + 1);
                }
            }
        }

        String nombre_universidad = getMaxKey(puntaje_universidad);
        String nombre_carrera = getMaxKey(puntaje_carrera);

        // Log de las universidades y carreras
        System.out.println("Universidad buscada: " + nombre_universidad);
        System.out.println("Carrera buscada: " + nombre_carrera);

        Carrera carrera = carreraRepository.findByNombre(nombre_carrera)
        .orElseThrow(() -> new ResourceNotFoundException("Carrera " + nombre_carrera + "no encontrada"));
        Universidad universidad = universidadRepository.findByNombre(nombre_universidad)
        .orElseThrow(() -> new ResourceNotFoundException("Universidad " + nombre_universidad + "no encontrada"));

        CarreraUniversidad carreraUniversidad = carreraUniversidadRepository.findByUniversidadAndCarrera(universidad.getId(), carrera.getId())
            .orElseThrow(() -> new ResourceNotFoundException("No se encontró la combinación de carrera y universidad"));

        Test test = testRepository.findById(test_id)
            .orElseThrow(() -> new ResourceNotFoundException("Test no encontrado con id " + test_id));

        Resultado resultado = new Resultado();
        resultado.setTest(test);
        resultado.setCarreraUniversidad(carreraUniversidad);
        resultado = resultadoRepository.save(resultado);
    }

    private <T> T getMaxKey(Map<T, Integer> map) {
        return map.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);

    }
  
    @Transactional(readOnly = true)
    public List<TestPreguntaResponseDTO> getResultadosTipoTest(Long testId, String tipoTest) {
        List<TestPregunta> preguntas = testPreguntaRepository.findByTestIdAndTipoTest(testId, tipoTest);
        return preguntas.stream()
                .map(testPreguntaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    private TestPreguntaResponseDTO mapToResponseDTO(TestPregunta testPregunta) {
        TestPreguntaResponseDTO dto = testPreguntaMapper.toDTO(testPregunta);
        Pregunta pregunta = testPregunta.getPregunta();
        PreguntaResponseDTO preguntaDTO = new PreguntaResponseDTO();
        preguntaDTO.setId(pregunta.getId());
        preguntaDTO.setEnunciado(pregunta.getEnunciado());
        dto.setPregunta_id(preguntaDTO);
        return dto;
    }
}
