package rs.tim14.xml.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.service.AutorskaPravaService;

@RestController
@RequestMapping("/autorska-prava")
@RequiredArgsConstructor
public class AutorskaPravaController {
    private final AutorskaPravaService autorskaPravaService;

    @PostMapping
    public ResponseEntity<ZahtevZaAutorskaPrava> create(@RequestBody ZahtevZaAutorskaPrava zahtev) throws Exception {
        return new ResponseEntity<>(autorskaPravaService.create(zahtev), HttpStatus.CREATED);
    }
}
