package rs.tim14.xml.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import rs.tim14.xml.dto.requests.NaprednaPretragaRequest;
import rs.tim14.xml.dto.requests.PretragaRequest;
import rs.tim14.xml.dto.responses.ZahteviZaAutorskaPravaDTO;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.repository.AutorskaPravaRepository;
import rs.tim14.xml.service.AutorskaPravaService;
import rs.tim14.xml.service.MetadataService;
import rs.tim14.xml.service.UploadFile;

@RestController
@RequestMapping("/autorska-prava")
@RequiredArgsConstructor
public class AutorskaPravaController {
    private final AutorskaPravaService autorskaPravaService;

    private final AutorskaPravaRepository autorskaPravaRepository;
    private final UploadFile uploadFile;

    private final MetadataService metadataService;
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
    public ResponseEntity<ZahteviZaAutorskaPravaDTO> getAll(){
        try {
            List<ZahtevZaAutorskaPrava> autorskaPrava = autorskaPravaService.getAll();
            ZahteviZaAutorskaPravaDTO autorskaPravaDTO = new ZahteviZaAutorskaPravaDTO(autorskaPrava);
            return new ResponseEntity<>(autorskaPravaDTO,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevZaAutorskaPrava> getById(@PathVariable String id){
        try {
            ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = autorskaPravaService.getById(id);
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
    public ResponseEntity<ZahteviZaAutorskaPravaDTO> dobaviPoTekstu(@RequestBody PretragaRequest pretragaRequest) {
        try {
            List<ZahtevZaAutorskaPrava> obrasci = autorskaPravaService.dobaviPoTekstu(pretragaRequest.getFilteri());
            ZahteviZaAutorskaPravaDTO autorskaPravaDTO = new ZahteviZaAutorskaPravaDTO(obrasci);
            return new ResponseEntity<>(autorskaPravaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping(path="/pretragaPoMetapodacima", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahteviZaAutorskaPravaDTO> pretragaPoMetapodacima(@RequestBody NaprednaPretragaRequest pretragaRequest) throws Exception {
        List<ZahtevZaAutorskaPrava> zahtevi = metadataService.dobaviPoMetapodacima(pretragaRequest);
        ZahteviZaAutorskaPravaDTO autorskaPravaDTO = new ZahteviZaAutorskaPravaDTO(zahtevi);
        return new ResponseEntity<>(autorskaPravaDTO, HttpStatus.OK);
    }

}
