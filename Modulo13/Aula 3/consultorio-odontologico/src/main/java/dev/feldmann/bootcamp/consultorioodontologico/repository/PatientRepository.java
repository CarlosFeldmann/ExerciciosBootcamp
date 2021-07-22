package dev.feldmann.bootcamp.consultorioodontologico.repository;

import dev.feldmann.bootcamp.consultorioodontologico.entity.Patient;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

  @Query("select p from Turn t "
      + "join t.patient p "
      + "where t.day = :date")
  List<Patient> getPatientsWithTurnIn(@Param("date") LocalDate date);

}
