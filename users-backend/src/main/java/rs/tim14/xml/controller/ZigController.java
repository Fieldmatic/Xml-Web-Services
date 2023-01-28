package rs.tim14.xml.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;
import rs.tim14.xml.service.ZigService;

@RestController
@RequestMapping("/zig")
@RequiredArgsConstructor
public class ZigController {
    private final ZigService zigService;

    @PostMapping
    public ResponseEntity<ZahtevZaPriznanjeZiga> create(@RequestBody ZahtevZaPriznanjeZiga zahtev) throws Exception {
        return new ResponseEntity<>(zigService.create(zahtev), HttpStatus.CREATED);
    }
}
