package dev.feldmann.bootcamp.consultorioodontologico.repository;

import dev.feldmann.bootcamp.consultorioodontologico.entity.Turn;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {


  @Query("select t from Turn t"
      + " join fetch t.turnStatus ts"
      + " where ts.name = :status"
      + " and (:day is null or t.day = :day)"
  )
  List<Turn> getTurnsWithStatus(@Param("status") String status,
      @Param("day") Optional<LocalDate> day);

  default List<Turn> getFinishedTurns() {
    return getTurnsWithStatus("Concluído", Optional.empty());
  }

  default List<Turn> getPendingTurnsAt(LocalDate date) {
    return getTurnsWithStatus("Pendente", Optional.of(date));
  }


}
