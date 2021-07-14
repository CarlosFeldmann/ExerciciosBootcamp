package dev.feldmann.bootcamp.wave2.diplomas.controller;

import dev.feldmann.bootcamp.wave2.diplomas.form.StudentForm;
import dev.feldmann.bootcamp.wave2.diplomas.response.DegreeResponse;
import dev.feldmann.bootcamp.wave2.diplomas.service.DegreeService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DegreeController {

    private final DegreeService degreeService;

    public DegreeController(DegreeService degreeService) {
        this.degreeService = degreeService;
    }

    @PostMapping("/analyzeNotes")
    public DegreeResponse diploma(@Valid @RequestBody StudentForm studentForm) {
        return degreeService.generateDiploma(studentForm);
    }
}
