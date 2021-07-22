package dev.feldmann.bootcamp.consultorioodontologico.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "dentists")
public class Dentist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="id_dentist")
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private String dni;

  @Column(nullable = false)
  private LocalDate birthDate;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String codeMp;

  @OneToMany(mappedBy = "dentist")
  private List<Diary> diary;

  public Dentist(String name, String lastName, String address, String dni,
      LocalDate birthDate, String phone, String email, String codeMp) {
    this.name = name;
    this.lastName = lastName;
    this.address = address;
    this.dni = dni;
    this.birthDate = birthDate;
    this.phone = phone;
    this.email = email;
    this.codeMp = codeMp;
  }
}
