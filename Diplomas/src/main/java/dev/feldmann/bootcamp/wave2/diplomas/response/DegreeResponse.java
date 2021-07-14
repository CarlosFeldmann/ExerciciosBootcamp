package dev.feldmann.bootcamp.wave2.diplomas.response;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class DegreeResponse {

  private String student;
  private Double average;
  private String message;
  private List<SubjectResponse> subjects;

  public DegreeResponse(String student, Double average, String message) {
    this.student = student;
    this.average = average;
    this.message = message;
    this.subjects = new ArrayList<>();
  }

  public void addSubject(String name, Integer note) {
    this.subjects.add(new SubjectResponse(name, note));
  }


  @Getter
  @AllArgsConstructor
  public static class SubjectResponse {

    private String name;
    private Integer note;
  }
}
