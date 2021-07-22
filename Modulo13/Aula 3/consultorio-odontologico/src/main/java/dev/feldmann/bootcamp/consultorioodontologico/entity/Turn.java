package dev.feldmann.bootcamp.consultorioodontologico.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "turns")
public class Turn {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_turn")
  private Long id;

  @Column(nullable = false)
  private LocalDate day;


  @ManyToOne
  @JoinColumn(name = "id_diary")
  private Diary diary;

  @ManyToOne
  @JoinColumn(name = "id_turn_status")
  private TurnStatus turnStatus;

  @ManyToOne
  @JoinColumn(name = "id_patient")
  private Patient patient;
}
