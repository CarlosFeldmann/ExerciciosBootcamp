package dev.feldmann.bootcamp.wave2.diplomas.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import dev.feldmann.bootcamp.wave2.diplomas.form.StudentForm;
import dev.feldmann.bootcamp.wave2.diplomas.form.StudentForm.Subject;
import dev.feldmann.bootcamp.wave2.diplomas.response.DegreeResponse;
import dev.feldmann.bootcamp.wave2.diplomas.service.DegreeService;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DegreeServiceTest {


  private DegreeService service;


  @BeforeEach
  void setUp() {
    service = new DegreeService();
  }

  @Test
  void generateMessageShouldReturnCongratulationsWithScoreEqualOrHigherThan9() {
    var result = service.generateMessage(9);

    assertThat(result)
        .isEqualTo("Parabéns! Aprovado acima da média!");
  }

  @Test
  void generateMessageShouldReturnApprovedWithScoreEqualOrHigherThan7() {
    var result = service.generateMessage(7);

    assertThat(result)
        .isEqualTo("Aprovado!");
  }

  @Test
  void generateMessageShouldReturnReprovedWithScoreLowerThan7() {
    var result = service.generateMessage(6.8);

    assertThat(result)
        .isEqualTo("Reprovado com sucesso!");
  }


  @Test
  void shouldReturnAverageZeroWhenAllTheGradesAreZero() {

    var form = new StudentForm(
        "cleitinhodabaixada",
        Arrays.asList(
            new Subject("Matematica", 0),
            new Subject("Quimica", 0)
        ));

    double result = service.calculateAverage(form);

    assertThat(result)
        .isZero();
  }

  @Test
  void shouldReturnCorrectAverageWithTwoSubjects() {

    var form = new StudentForm(
        "cleitinhodabaixada",
        Arrays.asList(
            new Subject("Matematica", 8),
            new Subject("Quimica", 6)
        ));

    double result = service.calculateAverage(form);

    assertThat(result)
        .isEqualTo(7d);
  }

  @Test
  void shouldConvertFormIntoResponse() {

    var msg = "SHOW DE BOLA MEU PARCEIRO!";
    var service = Mockito.spy(this.service);


    doReturn(8d).when(service).calculateAverage(any());
    doReturn(msg).when(service).generateMessage(anyDouble());

    var form = new StudentForm(
        "cleitinhodabaixada",
        Arrays.asList(
            new Subject("Matematica", 3),
            new Subject("Quimica", 4)
        ));

    DegreeResponse response = service.generateDiploma(form);

    assertThat(response.getStudent()).isEqualTo("cleitinhodabaixada");
    assertThat(response.getMessage()).isEqualTo(msg);
    assertThat(response.getAverage()).isEqualTo(8d);

    assertThat(response.getSubjects())
        .extracting((s) -> tuple(s.getName(), s.getNote()))
        .contains(tuple("Matematica", 3), tuple("Quimica", 4));


  }
}