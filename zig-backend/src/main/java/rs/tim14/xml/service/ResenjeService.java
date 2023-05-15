package rs.tim14.xml.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.tim14.xml.dto.request.ObradaZahteva;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ResenjeZahteva;
import rs.tim14.xml.repository.ResenjeRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ResenjeService {
    private final ZigService zigService;

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

        zigService.setObradjen(obradaZahteva.getId(), obradaZahteva.isOdbijen());
    }
}
