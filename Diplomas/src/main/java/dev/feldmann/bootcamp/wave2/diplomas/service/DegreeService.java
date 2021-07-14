package dev.feldmann.bootcamp.wave2.diplomas.service;

import dev.feldmann.bootcamp.wave2.diplomas.form.StudentForm;
import dev.feldmann.bootcamp.wave2.diplomas.response.DegreeResponse;
import org.springframework.stereotype.Service;

@Service
public class DegreeService {

  private static final double PASS_SCORE = 7d;
  private static final double CONGRATULATIONS_SCORE = 9d;


  public DegreeResponse generateDiploma(StudentForm form) {
    double avg = calculateAverage(form);
    String message = generateMessage(avg);

    var response = new DegreeResponse(form.getName(), avg, message);

    form.getSubjects()
        .forEach(subject -> response.addSubject(subject.getName(), subject.getNote()));

    return response;
  }


  public String generateMessage(double avg) {
    if (avg >= CONGRATULATIONS_SCORE) {
      return "Parabéns! Aprovado acima da média!";
    } else if (avg >= PASS_SCORE) {
      return "Aprovado!";
    } else {
      return "Reprovado com sucesso!";
    }
  }

  public double calculateAverage(StudentForm form) {
    return form.getSubjects().stream()
               .mapToDouble(d -> d.getNote().doubleValue())
               .average()
               .orElse(0);
  }
}
