package dev.feldmann.bootcamp.consultorioodontologico.entity.temp;

import dev.feldmann.bootcamp.consultorioodontologico.entity.Patient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Getter;

@Getter
public class DentistScheduleEntry {

  private LocalDateTime start;
  private LocalDateTime end;
  private Patient patient;

  public DentistScheduleEntry(LocalDate date,
      LocalTime startTime,
      LocalTime endTime,
      Patient patient) {

    this.start = startTime.atDate(date);
    this.end = endTime.atDate(date);
    this.patient = patient;
  }
}
