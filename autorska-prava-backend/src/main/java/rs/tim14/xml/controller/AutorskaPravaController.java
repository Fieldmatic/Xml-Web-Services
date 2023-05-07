package rs.tim14.xml.controller;

import java.io.*;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import rs.tim14.xml.dto.responses.ZahteviZaAutorskaPravaDTO;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.service.AutorskaPravaService;
import rs.tim14.xml.service.UploadFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/autorska-prava")
@RequiredArgsConstructor
public class AutorskaPravaController {
    private final AutorskaPravaService autorskaPravaService;
    private final UploadFile uploadFile;

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
    public byte[] getHTML(@PathVariable String id) throws Exception {
        return autorskaPravaService.getHTML(id);
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPDF(@PathVariable String id) throws Exception {
        return autorskaPravaService.getPDF(id);
    }

    @GetMapping(path = "/rdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateRDF(@PathVariable String id) {
        ByteArrayInputStream byteFile = autorskaPravaService.getRDF(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=metadata_" + id + ".rdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }

    @GetMapping(path = "/json/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateJSON(@PathVariable String id) {
        ByteArrayInputStream byteFile = autorskaPravaService.getJSON(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=metadata_" + id + ".json");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }
}
