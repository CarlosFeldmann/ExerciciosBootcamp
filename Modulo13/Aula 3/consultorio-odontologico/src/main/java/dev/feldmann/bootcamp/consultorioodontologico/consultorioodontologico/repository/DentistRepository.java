package dev.feldmann.bootcamp.consultorioodontologico.consultorioodontologico.repository;

import dev.feldmann.bootcamp.consultorioodontologico.consultorioodontologico.entity.Dentist;
import dev.feldmann.bootcamp.consultorioodontologico.consultorioodontologico.entity.Patient;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository<Long, Patient> {

  @Query("select distinct den from Dentist den "
      + "join den.diary diary "
      + "join diary.turn turn "
      + "having count(turn) >= :turn_amount")
  List<Dentist> getDentistsWithTurnsAtDate(@Param("turn_amount") Integer turnAmount,
      @Param("date") LocalDate date);

}
