package rs.tim14.xml.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.tim14.xml.dto.AllResponse;
import rs.tim14.xml.dto.request.ObradaZahteva;
import rs.tim14.xml.dto.requests.NaprednaPretragaRequest;
import rs.tim14.xml.dto.requests.PretragaRequest;
import rs.tim14.xml.dto.responses.ZahteviZaPriznanjeZigaDTO;
import rs.tim14.xml.dto.request.IzvestajRequest;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ResenjeZahteva;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.TStatusZahteva;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.VrstaPriloga;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;
import rs.tim14.xml.service.MetadataService;
import rs.tim14.xml.service.ResenjeService;
import rs.tim14.xml.service.UploadFile;
import rs.tim14.xml.service.ZigService;

import java.io.ByteArrayInputStream;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/zig")
@RequiredArgsConstructor
public class ZigController {
    private final ZigService zigService;
    private final UploadFile uploadFile;
    private final MetadataService metadataService;
    private final ResenjeService resenjeService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ZahteviZaPriznanjeZigaDTO getRequestById(@RequestParam String id) throws Exception {
        List<ZahtevZaPriznanjeZiga> zahtevi = new ArrayList<>(Collections.singleton(zigService.get(id)));
        ZahteviZaPriznanjeZigaDTO zigDTO = new ZahteviZaPriznanjeZigaDTO(zahtevi);
        return zigDTO;
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahteviZaPriznanjeZigaDTO> getAll(@RequestHeader("role") String role) {
        try {
            List<ZahtevZaPriznanjeZiga> zahteviZaPriznanjePatenta = zigService.getAll();
            if (role.equals("Klijent")) {
                zahteviZaPriznanjePatenta = zahteviZaPriznanjePatenta.stream().filter((zahtevZaPriznanjePatenta -> zahtevZaPriznanjePatenta.getStatusZahteva().equals(TStatusZahteva.PRIHVACEN))).collect(Collectors.toList());
            }
            ZahteviZaPriznanjeZigaDTO zahteviZaPriznanjePatentaDTO = new ZahteviZaPriznanjeZigaDTO(zahteviZaPriznanjePatenta);
            return new ResponseEntity<>(zahteviZaPriznanjePatentaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/html", produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public byte[] getHTML(@RequestParam String id) throws Exception {
        return zigService.getHTML(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPDF(@RequestParam String id) throws Exception {
        return zigService.getPDF(id);
    }

    @PostMapping(value = "/uploadFile/{fileType}", produces = "text/plain")
    public ResponseEntity<String> uploadFile(@PathVariable(name = "fileType") VrstaPriloga fileType, @RequestBody MultipartFile file)  {
        String fileName = this.uploadFile.execute(file, fileType);
        if(fileName.compareTo("")==0){
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(fileName,HttpStatus.CREATED);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ZahtevZaPriznanjeZiga create(@RequestBody ZahtevZaPriznanjeZiga zahtev) throws Exception {
        return zigService.create(zahtev);
    }

    @GetMapping(path = "/rdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<String> generateRDF(@PathVariable String id) {
        String result = zigService.getRDF(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", id.concat(".rdf"));
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @GetMapping(path = "/json/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<String> generateJSON(@PathVariable String id) {
        String result = zigService.getJSON(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", id.concat(".json"));
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/pretragaPoTekstu", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahteviZaPriznanjeZigaDTO> dobaviPoTekstu(@RequestBody PretragaRequest pretragaRequest, @RequestHeader("role") String role) {
        try {
            List<ZahtevZaPriznanjeZiga> zahtevi = zigService.dobaviPoTekstu(pretragaRequest.getFilteri());
            if (role.equals("Klijent")) {
                zahtevi = zahtevi.stream().filter(zahtev -> zahtev.getStatusZahteva().equals(TStatusZahteva.PRIHVACEN)).collect(Collectors.toList());
            }
            ZahteviZaPriznanjeZigaDTO zahteviDTO = new ZahteviZaPriznanjeZigaDTO(zahtevi);
            return new ResponseEntity<>(zahteviDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="/pretragaPoMetapodacima", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahteviZaPriznanjeZigaDTO> pretragaPoMetapodacima(@RequestBody NaprednaPretragaRequest pretragaRequest,  @RequestHeader("role") String role) throws Exception {
        List<ZahtevZaPriznanjeZiga> zahtevi = metadataService.dobaviPoMetapodacima(pretragaRequest);
        if (role.equals("Klijent")) {
            zahtevi = zahtevi.stream().filter(zahtev -> zahtev.getStatusZahteva().equals(TStatusZahteva.PRIHVACEN)).collect(Collectors.toList());
        }
        ZahteviZaPriznanjeZigaDTO zahteviDTO = new ZahteviZaPriznanjeZigaDTO(zahtevi);
        return new ResponseEntity<>(zahteviDTO, HttpStatus.OK);
    }
    
    @PostMapping(path = "/izvestaj", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateIzvestaj(@RequestBody IzvestajRequest izvestajRequest) throws Exception {
        ByteArrayInputStream result = zigService.getReport(izvestajRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", ("izvestaj.pdf"));
        return new ResponseEntity<>(new InputStreamResource(result), headers, HttpStatus.OK);
    }

    @PostMapping(value = "/obradi-zahtev")
    public void obradiZahtev(@RequestBody ObradaZahteva obradaZahteva) throws Exception {
        resenjeService.obradiZahtev(obradaZahteva);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/pdf-resource/{path}/{name}", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPDFResource(@PathVariable String path, @PathVariable String name) throws Exception {
        String filePath = "./zig-backend/src/main/resources/z1/" + path + "/" + name;
        return Files.readAllBytes(Paths.get(filePath));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/resenje", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getResenje(@RequestParam String id) throws Exception {
        String filePath = "./zig-backend/data/result/resenja/" + id + ".pdf";
        return Files.readAllBytes(Paths.get(filePath));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/data/{path}/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getData(@PathVariable String path, @PathVariable String name) throws Exception {
        String filePath = "./zig-backend/data/result/" + path + "/" + name;
        return Files.readAllBytes(Paths.get(filePath));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/resource/{path}/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getResource(@PathVariable String path, @PathVariable String name) throws Exception {
        String filePath = "./zig-backend/src/main/resources/z1/" + path + "/" + name;
        return Files.readAllBytes(Paths.get(filePath));
    }
}
