package rs.tim14.xml.controller;

import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.tim14.xml.dto.ZahteviZaPriznanjePatentaDTO;
import rs.tim14.xml.dto.request.IzvestajRequest;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;
import rs.tim14.xml.repository.PatentRepository;
import rs.tim14.xml.service.ZahtevZaPriznanjePatentaService;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/patent")
@RequiredArgsConstructor
public class PatentController {
    private final ZahtevZaPriznanjePatentaService zahtevZaPriznanjePatentaService;
    private final PatentRepository patentRepository;

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
    public ResponseEntity<ZahteviZaPriznanjePatentaDTO> getAll() {
        try {
            List<ZahtevZaPriznanjePatenta> zahteviZaPriznanjePatenta = zahtevZaPriznanjePatentaService.getAll();
            ZahteviZaPriznanjePatentaDTO zahteviZaPriznanjePatentaDTO = new ZahteviZaPriznanjePatentaDTO(zahteviZaPriznanjePatenta);
            return new ResponseEntity<>(zahteviZaPriznanjePatentaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/html/{id}", produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public ResponseEntity<byte[]> getHTML(@PathVariable String id) throws Exception {
        byte[] html = zahtevZaPriznanjePatentaService.getHTML(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", id.concat(".html"));
        return new ResponseEntity<>(html, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPDF(@PathVariable String id) throws Exception {
        byte[] pdf = zahtevZaPriznanjePatentaService.getPDF(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", id.concat(".pdf"));
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

    @GetMapping(path = "/rdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<String> generateRDF(@PathVariable String id) {
        String result = zahtevZaPriznanjePatentaService.getRDF(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", id.concat(".rdf"));
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping(path = "/json/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<String> generateJSON(@PathVariable String id) {
        String result = zahtevZaPriznanjePatentaService.getJSON(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", id.concat(".json"));
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping(path = "/izvestaj", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateIzvestaj(@RequestBody IzvestajRequest izvestajRequest) throws IOException, DatatypeConfigurationException, DocumentException {
        ByteArrayInputStream result = zahtevZaPriznanjePatentaService.getReport(izvestajRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", ("izvestaj.pdf"));
        return new ResponseEntity<>(new InputStreamResource(result), headers, HttpStatus.OK);
    }


}
