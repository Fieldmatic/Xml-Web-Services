package rs.tim14.xml.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.tim14.xml.dto.requests.ObradaZahteva;
import rs.tim14.xml.model.autorska_prava.ResenjeZahteva;
import rs.tim14.xml.repository.ResenjeRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ResenjeService {
    private final AutorskaPravaService autorskaPravaService;

    public void obradiZahtev(ObradaZahteva obradaZahteva) throws Exception {
        ResenjeZahteva resenjeZahteva = new ResenjeZahteva();
        resenjeZahteva.setBrojPrijave(obradaZahteva.getId());
        resenjeZahteva.setImeSluzbenika(obradaZahteva.getImeSluzbenika());
        resenjeZahteva.setPrezimeSluzbenika(obradaZahteva.getPrezimeSluzbenika());
        resenjeZahteva.setDatumObrade(new Date());
        resenjeZahteva.setOdbijen(obradaZahteva.isOdbijen());
        if (resenjeZahteva.isOdbijen())
            resenjeZahteva.setRazlogOdbijanja(obradaZahteva.getRazlogOdbijanja());
        else
            resenjeZahteva.setSifra(obradaZahteva.getId());
        ResenjeRepository.write(resenjeZahteva);

        autorskaPravaService.setObradjen(obradaZahteva.getId(), obradaZahteva.isOdbijen());
        // poslati mail
    }
}
