package rs.tim14.xml.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.tim14.xml.dto.ZahteviZaPriznanjePatentaDTO;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;
import rs.tim14.xml.service.ZahtevZaPriznanjePatentaService;

import java.util.List;

@RestController
@RequestMapping("/patent")
@RequiredArgsConstructor
public class PatentController {
    private final ZahtevZaPriznanjePatentaService zahtevZaPriznanjePatentaService;

    @PostMapping
    public ResponseEntity<ZahtevZaPriznanjePatenta> create(@RequestBody ZahtevZaPriznanjePatenta zahtev) throws Exception {
        return new ResponseEntity<>(zahtevZaPriznanjePatentaService.create(zahtev), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevZaPriznanjePatenta> getById(@PathVariable String id){
        try {
            ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = zahtevZaPriznanjePatentaService.getById(id);
            return new ResponseEntity<>(zahtevZaPriznanjePatenta, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahteviZaPriznanjePatentaDTO> getAll(){
        try {
            List<ZahtevZaPriznanjePatenta> zahteviZaPriznanjePatenta = zahtevZaPriznanjePatentaService.getAll();
            ZahteviZaPriznanjePatentaDTO zahteviZaPriznanjePatentaDTO = new ZahteviZaPriznanjePatentaDTO(zahteviZaPriznanjePatenta);
            return new ResponseEntity<>(zahteviZaPriznanjePatentaDTO,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
