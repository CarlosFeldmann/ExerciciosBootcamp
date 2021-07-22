package dev.feldmann.bootcamp.consultorioodontologico.repository;

import dev.feldmann.bootcamp.consultorioodontologico.entity.Diary;
import dev.feldmann.bootcamp.consultorioodontologico.entity.Turn;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

}
