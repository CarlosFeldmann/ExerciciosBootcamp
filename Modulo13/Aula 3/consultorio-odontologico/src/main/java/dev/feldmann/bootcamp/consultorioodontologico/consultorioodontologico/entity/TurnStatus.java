package dev.feldmann.bootcamp.consultorioodontologico.consultorioodontologico.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "turn_status")
public class TurnStatus {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_turn_status")
  private Long id;


  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;



}
