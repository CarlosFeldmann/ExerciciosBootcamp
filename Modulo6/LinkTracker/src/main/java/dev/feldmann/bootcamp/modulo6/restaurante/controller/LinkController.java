package dev.feldmann.bootcamp.modulo6.restaurante.controller;


import dev.feldmann.bootcamp.modulo6.restaurante.dto.LinkDTO;
import dev.feldmann.bootcamp.modulo6.restaurante.forms.CreateLinkForm;
import dev.feldmann.bootcamp.modulo6.restaurante.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/link/")
public class LinkController {


    @Autowired
    private LinkService service;


    @GetMapping("/{id}/info")
    public ResponseEntity<LinkDTO> getInfo(
            @PathVariable Long id,
            @RequestParam(required = false) @NotEmpty String password) {
        return ResponseEntity.ok(service.getInfo(id, password));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> redirect(
            @PathVariable Long id,
            @RequestParam(required = false) @NotEmpty String password) {
        String redirectUrl = service.getRedirectUrl(id, password);
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .location(URI.create(redirectUrl))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable Long id,
            @RequestParam(required = false) @NotEmpty String password) {
        service.delete(id, password);
    }


    @PostMapping
    public ResponseEntity<LinkDTO> createLink(@Valid @RequestBody CreateLinkForm form) {
        LinkDTO link = service.createLink(form.getUrl(), form.getPassword());

        URI resourceUri = MvcUriComponentsBuilder
                .fromMethodCall(on(LinkController.class).getInfo(link.getId(), null))
                .buildAndExpand().encode().toUri();


        return ResponseEntity.created(resourceUri).body(link);
    }


}
