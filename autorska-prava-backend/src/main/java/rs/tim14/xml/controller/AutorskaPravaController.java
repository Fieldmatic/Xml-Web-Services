package rs.tim14.xml.controller;

import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.tim14.xml.dto.requests.IzvestajRequest;
import rs.tim14.xml.dto.requests.NaprednaPretragaRequest;
import rs.tim14.xml.dto.requests.PretragaRequest;
import rs.tim14.xml.dto.responses.ZahteviZaAutorskaPravaDTO;
import rs.tim14.xml.model.autorska_prava.TStatusZahteva;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.service.AutorskaPravaService;
import rs.tim14.xml.service.MetadataService;
import rs.tim14.xml.service.UploadFile;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autorska-prava")
@RequiredArgsConstructor
public class AutorskaPravaController {
    private final AutorskaPravaService autorskaPravaService;

    private final UploadFile uploadFile;

    private final MetadataService metadataService;
    private static final String resourcePath = "http://192.168.1.18:8081/autorska-prava-backend/src/main/resources/a1/";
    @PostMapping
    public ResponseEntity<ZahtevZaAutorskaPrava> create(@RequestBody ZahtevZaAutorskaPrava zahtev) throws Exception {
        return new ResponseEntity<>(autorskaPravaService.create(zahtev), HttpStatus.CREATED);
    }

    @PostMapping(value = "/uploadFile/{fileType}", produces = "text/plain")
    public ResponseEntity<String> uploadFile(@PathVariable(name = "fileType") boolean fileType, @RequestBody MultipartFile file)  {
        String fileName = this.uploadFile.execute(file, fileType);
        if(fileName.compareTo("")==0){
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(fileName,HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahteviZaAutorskaPravaDTO> getAll(@RequestHeader("role") String role) {
        try {
            List<ZahtevZaAutorskaPrava> autorskaPrava = autorskaPravaService.getAll();
            if (role.equals("Klijent")) {
                autorskaPrava = autorskaPrava.stream().filter((zahtev -> zahtev.getStatusZahteva().equals(TStatusZahteva.PRIHVACEN))).collect(Collectors.toList());
            }
            ZahteviZaAutorskaPravaDTO autorskaPravaDTO = new ZahteviZaAutorskaPravaDTO(autorskaPrava);
            return new ResponseEntity<>(autorskaPravaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevZaAutorskaPrava> getById(@PathVariable String id){
        try {
            ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = autorskaPravaService.getById(id);
            if (zahtevZaAutorskaPrava.getAutorskoDelo().getPrimerAutorskogDela().getPutanjaDoPrimera() != null && !Objects.equals(zahtevZaAutorskaPrava.getAutorskoDelo().getPrimerAutorskogDela().getPutanjaDoPrimera(), "")) {
                String path = resourcePath + zahtevZaAutorskaPrava.getAutorskoDelo().getPrimerAutorskogDela().getPutanjaDoPrimera();
                zahtevZaAutorskaPrava.getAutorskoDelo().getPrimerAutorskogDela().setPutanjaDoPrimera(path);
            }
            if (zahtevZaAutorskaPrava.getAutorskoDelo().getOpisAutorskogDela().getPutanjaDoOpisa() != null && !Objects.equals(zahtevZaAutorskaPrava.getAutorskoDelo().getOpisAutorskogDela().getPutanjaDoOpisa(), "")) {
                String path = resourcePath + zahtevZaAutorskaPrava.getAutorskoDelo().getOpisAutorskogDela().getPutanjaDoOpisa();
                zahtevZaAutorskaPrava.getAutorskoDelo().getOpisAutorskogDela().setPutanjaDoOpisa(path);
            }
            return new ResponseEntity<>(zahtevZaAutorskaPrava, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/html/{id}", produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public ResponseEntity<byte[]> getHTML(@PathVariable String id) throws Exception {
        byte[] html = autorskaPravaService.getHTML(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", id.concat(".html"));
        return new ResponseEntity<>(html, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPDF(@PathVariable String id) throws Exception {
        byte[] pdf = autorskaPravaService.getPDF(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", id.concat(".pdf"));
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

    @GetMapping(path = "/rdf/{id}", produces = "application/rdf+xml")
    public ResponseEntity<String> generateRDF(@PathVariable String id) {
        String result = autorskaPravaService.getRDF(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", id.concat(".rdf"));
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping(path = "/json/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateJSON(@PathVariable String id) {
        String result = autorskaPravaService.getJSON(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", id.concat(".json"));
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/pretragaPoTekstu", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahteviZaAutorskaPravaDTO> dobaviPoTekstu(@RequestBody PretragaRequest pretragaRequest, @RequestHeader("role") String role) {
        try {
            List<ZahtevZaAutorskaPrava> obrasci = autorskaPravaService.dobaviPoTekstu(pretragaRequest.getFilteri());
            if (role.equals("Klijent")) {
                obrasci = obrasci.stream().filter((zahtev -> zahtev.getStatusZahteva().equals(TStatusZahteva.PRIHVACEN))).collect(Collectors.toList());
            }
            ZahteviZaAutorskaPravaDTO autorskaPravaDTO = new ZahteviZaAutorskaPravaDTO(obrasci);
            return new ResponseEntity<>(autorskaPravaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/pretragaPoMetapodacima", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahteviZaAutorskaPravaDTO> pretragaPoMetapodacima(@RequestBody NaprednaPretragaRequest pretragaRequest, @RequestHeader("role") String role) throws Exception {
        List<ZahtevZaAutorskaPrava> zahtevi = metadataService.dobaviPoMetapodacima(pretragaRequest);
        if (role.equals("Klijent")) {
            zahtevi = zahtevi.stream().filter((zahtev -> zahtev.getStatusZahteva().equals(TStatusZahteva.PRIHVACEN))).collect(Collectors.toList());
        }
        ZahteviZaAutorskaPravaDTO autorskaPravaDTO = new ZahteviZaAutorskaPravaDTO(zahtevi);
        return new ResponseEntity<>(autorskaPravaDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/izvestaj", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateIzvestaj(@RequestBody IzvestajRequest izvestajRequest) throws IOException, DatatypeConfigurationException, DocumentException {
        ByteArrayInputStream result = autorskaPravaService.getReport(izvestajRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", ("izvestaj.pdf"));
        return new ResponseEntity<>(new InputStreamResource(result), headers, HttpStatus.OK);
    }

}
