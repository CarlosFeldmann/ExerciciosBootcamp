package dev.feldmann.bootcamp.modulo6.restaurante.service;


import dev.feldmann.bootcamp.modulo6.restaurante.entity.Link;
import dev.feldmann.bootcamp.modulo6.restaurante.dto.LinkDTO;
import dev.feldmann.bootcamp.modulo6.restaurante.exceptions.ResourceNotFoundException;
import dev.feldmann.bootcamp.modulo6.restaurante.exceptions.UnauthorizedAccessException;
import dev.feldmann.bootcamp.modulo6.restaurante.repository.LinkRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Service
public class LinkService {


    private LinkRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LinkService(LinkRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @SneakyThrows
    public LinkDTO createLink(String url, String password) {
        String passwordHash = password != null ? passwordEncoder.encode(password) : null;
        Link link = new Link(url, passwordHash);
        repository.save(link);
        return LinkDTO.fromLink(link);
    }

    @SneakyThrows
    public LinkDTO getInfo(Long linkId, String password) {
        var link = this.repository.findById(linkId).filter(Link::isValid);
        if (link.isEmpty()) {
            throw new ResourceNotFoundException(linkId);
        }
        validateAccess(link.get(), password);
        return LinkDTO.fromLink(link.get());
    }


    @SneakyThrows
    public String getRedirectUrl(Long linkId, String password) {
        var link = this.repository.findById(linkId).filter(Link::isValid);
        if (link.isEmpty()) {
            throw new ResourceNotFoundException(linkId);
        }
        validateAccess(link.get(), password);
        link.get().click();
        return link.get().getUrl();
    }



    @SneakyThrows
    public void delete(Long linkId, String password) {
        Optional<Link> byId = this.repository.findById(linkId);
        if (byId.isEmpty()) {
            throw new ResourceNotFoundException(linkId);
        }
        var link = byId.get();
        validateAccess(link, password);
        link.delete();
        repository.save(link);
    }

    private void validateAccess(Link link, String providedPassword) {
        String linkPassword = link.getPassword();
        if (linkPassword != null && (providedPassword == null || !passwordEncoder.matches(providedPassword, linkPassword))) {
            throw new UnauthorizedAccessException(link.getId());
        }
    }

}
