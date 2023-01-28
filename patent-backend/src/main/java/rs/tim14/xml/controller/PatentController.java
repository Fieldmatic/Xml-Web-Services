package rs.tim14.xml.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;
import rs.tim14.xml.service.ZahtevZaPriznanjePatentaService;

@RestController
@RequestMapping("/patent")
@RequiredArgsConstructor
public class PatentController {
    private final ZahtevZaPriznanjePatentaService zahtevZaPriznanjePatentaService;

    @PostMapping
    public ResponseEntity<ZahtevZaPriznanjePatenta> create(@RequestBody ZahtevZaPriznanjePatenta zahtev) throws Exception {
        return new ResponseEntity<>(zahtevZaPriznanjePatentaService.create(zahtev), HttpStatus.CREATED);
    }
}
