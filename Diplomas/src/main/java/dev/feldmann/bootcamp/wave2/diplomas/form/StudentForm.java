package dev.feldmann.bootcamp.wave2.diplomas.form;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentForm {

  @NotEmpty
  @NotNull
  @Size(min = 8, max = 50)
  @Pattern(regexp = "^[a-zA-Z]+$")
  private String name;

  @Valid
  @NotNull
  @Size(min = 1)
  private List<Subject> subjects;


  @Setter
  @Getter
  @AllArgsConstructor
  public static class Subject {

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 50)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String name;

    @NotNull
    @Range(min = 0, max = 10)
    private Integer note;

  }


}
