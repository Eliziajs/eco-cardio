package domain.service;

import domain.ports.portOut.repository.ExameRepository;
import domain.model.Exame;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class ExameService {

    @Inject
    domain.ports.portIn.service.ExameService exameService;

    @InjectMock
    ExameRepository repositoryMock;

    void testCreateExame(){
        Exame exameInput = new Exame();


    }

/** @Test
 @Transactional
 void testCreateExame() {
 // Arrange
 Exame exameInput = new Exame();
 exameInput.setNome("Hemograma");
 exameInput.setDescricao("Exame de sangue completo");

 Exame exameSalvo = new Exame();
 exameSalvo.setId(1L);
 exameSalvo.setNome("Hemograma");
 exameSalvo.setDescricao("Exame de sangue completo");

 Mockito.when(exameRepositoy.save(exameInput)).thenReturn(exameSalvo);

 // Act
 Exame resultado = exameService.create(exameInput);

 // Assert
 assertNotNull(resultado);
 assertEquals(1L, resultado.getId());
 assertEquals("Hemograma", resultado.getNome());
 assertEquals("Exame de sangue completo", resultado.getDescricao());

 // Verifica se o método save foi chamado
 Mockito.verify(exameRepositoy).save(exameInput);
 }**/

}
