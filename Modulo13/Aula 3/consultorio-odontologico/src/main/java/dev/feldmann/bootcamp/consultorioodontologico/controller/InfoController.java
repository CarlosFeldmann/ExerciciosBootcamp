package dev.feldmann.bootcamp.consultorioodontologico.controller;

import dev.feldmann.bootcamp.consultorioodontologico.entity.Patient;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info/")
public class InfoController {


  @RequestMapping("/patientsAtDate")
  public List<Patient> getPatientsAtDay() {
    return new ArrayList<>();
  }


}
