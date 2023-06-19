package rs.tim14.xml.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.tim14.xml.dto.requests.ObradaZahteva;
import rs.tim14.xml.service.ResenjeService;

@RestController
@RequestMapping("/resenje")
@RequiredArgsConstructor
public class ResenjeController {
    private final ResenjeService resenjeService;

    @PostMapping(value = "/obradi-zahtev")
    public void obradiZahtev(@RequestBody ObradaZahteva obradaZahteva) throws Exception {
        resenjeService.obradiZahtev(obradaZahteva);
    }


}
