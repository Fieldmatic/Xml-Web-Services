package rs.tim14.xml.service;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import rs.tim14.xml.dto.request.ObradaZahteva;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ResenjeZahteva;
import rs.tim14.xml.repository.ResenjeRepository;
import rs.tim14.xml.xslfo.XSLFOTransformer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ResenjeService {
    private final ZigService zigService;
    private final ResenjeRepository resenjeRepository;

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
        resenjeZahteva = ResenjeRepository.write(resenjeZahteva);

        getPDF(resenjeZahteva);

        String link = "http://localhost:7005/api/zig/resenje?id=" + obradaZahteva.getId();

        // Generate QR code from the link
        QRCode qrCode = (QRCode) QRCode.from(link)
                .to(ImageType.PNG)
                .withSize(250, 250); // You can adjust the size as needed

        ByteArrayOutputStream qrCodeOutputStream = qrCode.stream();
        byte[] qrCodeByteArray = qrCodeOutputStream.toByteArray();

        String filePath = "./zig-backend/data/result/resenja/" + obradaZahteva.getId() + ".png";
        try (FileOutputStream qrCodeFileOutputStream = new FileOutputStream(filePath)) {
            qrCodeFileOutputStream.write(qrCodeByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        zigService.setObradjen(obradaZahteva.getId(), obradaZahteva.isOdbijen());
    }

    public byte[] getPDF(final ResenjeZahteva resenjeZahteva) throws Exception {
        final String xmlPath = resenjeRepository.getResenjeZahtevaPath(resenjeZahteva);
        final String resultPath = "./zig-backend/data/result/resenja/" + resenjeZahteva.getBrojPrijave() + ".pdf";
        final XSLFOTransformer xslfoTransformer = new XSLFOTransformer();
        xslfoTransformer.generatePDF(xmlPath, "./zig-backend/data/xsl_fo/z1_resenje_fo.xsl", resultPath);
        return FileUtils.readFileToByteArray(new File(resultPath));
    }
}
