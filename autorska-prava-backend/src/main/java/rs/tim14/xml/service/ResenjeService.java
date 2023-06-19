package rs.tim14.xml.service;

import lombok.RequiredArgsConstructor;

import org.apache.commons.io.FileUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import rs.tim14.xml.dto.requests.ObradaZahteva;
import rs.tim14.xml.model.autorska_prava.ResenjeZahteva;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.repository.ResenjeRepository;
import rs.tim14.xml.xslfo.XSLFOTransformer;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ResenjeService {
    private final AutorskaPravaService autorskaPravaService;

    private final ResenjeRepository resenjeRepository;

    private final MailService mailService;

    public void obradiZahtev(ObradaZahteva obradaZahteva) throws Exception {
        ResenjeZahteva resenjeZahteva = new ResenjeZahteva();
        resenjeZahteva.setBrojPrijave(obradaZahteva.getId());
        resenjeZahteva.setEmailSluzbenika(obradaZahteva.getEmailSluzbenika());
        resenjeZahteva.setImeSluzbenika(obradaZahteva.getImeSluzbenika());
        resenjeZahteva.setPrezimeSluzbenika(obradaZahteva.getPrezimeSluzbenika());
        resenjeZahteva.setDatumObrade(new Date());
        resenjeZahteva.setOdbijen(obradaZahteva.isOdbijen());
        if (resenjeZahteva.isOdbijen())
            resenjeZahteva.setRazlogOdbijanja(obradaZahteva.getRazlogOdbijanja());
        else
            resenjeZahteva.setSifra(obradaZahteva.getId());
        ResenjeRepository.write(resenjeZahteva);

        ZahtevZaAutorskaPrava a1 = autorskaPravaService.setObradjen(obradaZahteva.getId(), obradaZahteva.isOdbijen());
        mailService.sendMailWithAttachment(a1.getPodnosilac().getEmail().getValue(), resenjeZahteva, getPDF(resenjeZahteva));
    }

    public byte[] getPDF(final ResenjeZahteva resenjeZahteva) throws Exception {
        final String xmlPath = resenjeRepository.getResenjeZahtevaPath(resenjeZahteva);
        final String resultPath = "./autorska-prava-backend/data/result/resenja/" + resenjeZahteva.getBrojPrijave() + ".pdf";
        final XSLFOTransformer xslfoTransformer = new XSLFOTransformer();
        xslfoTransformer.generatePDF(xmlPath, "./autorska-prava-backend/data/xsl_fo/a1_resenje_fo.xsl", resultPath);
        return FileUtils.readFileToByteArray(new File(resultPath));
    }
}
