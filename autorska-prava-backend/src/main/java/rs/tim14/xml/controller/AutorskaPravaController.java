package rs.tim14.xml.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.service.AutorskaPravaService;
import rs.tim14.xml.service.UploadFile;

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
}
