package dev.feldmann.bootcamp.consultorioodontologico.repository;

import dev.feldmann.bootcamp.consultorioodontologico.entity.Dentist;
import dev.feldmann.bootcamp.consultorioodontologico.entity.temp.DentistScheduleEntry;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {

  @Query("select distinct den from Dentist den "
      + "join den.diary diary "
      + "join diary.turn turn "
      + "where turn.day = :date "
      + "group by den "
      + "having count(turn) >= :turn_amount"
  )
  List<Dentist> getDentistsWithTurnsAtDate(@Param("turn_amount") Integer turnAmount,
      @Param("date") LocalDate date);


  @Query("select "
      + "new dev.feldmann.bootcamp.consultorioodontologico.entity.temp.DentistScheduleEntry(t.day, d.startTime,d.endingTime ,p) "
      + "from Turn t "
      + "join t.diary d "
      + "join t.patient p"
  )
  List<DentistScheduleEntry> getSchedule(@Param("dentist") Dentist dentist);
}
