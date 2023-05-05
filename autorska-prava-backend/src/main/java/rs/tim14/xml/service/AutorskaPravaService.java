package rs.tim14.xml.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;

import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.autorska_prava.PodaciOAutorima;
import rs.tim14.xml.model.autorska_prava.TStatusZahteva;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.model.korisnici.TFizickoLice;
import rs.tim14.xml.model.korisnici.TLice;
import rs.tim14.xml.model.korisnici.TPravnoLice;
import rs.tim14.xml.rdf.FusekiWriter;
import rs.tim14.xml.rdf.MetadataExtractor;
import rs.tim14.xml.repository.AutorskaPravaRepository;
import rs.tim14.xml.repository.Repo;
import rs.tim14.xml.util.Util;
import rs.tim14.xml.repository.ExistDbManager;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorskaPravaService {
    private final Repo repo;
    private final MetadataExtractor metadataExtractor;
    private final FusekiWriter fusekiWriter;

    private final AutorskaPravaRepository autorskaPravaRepository;

    private final JaxbParser jaxbParser;

    public ZahtevZaAutorskaPrava create(ZahtevZaAutorskaPrava zahtev) throws Exception {
        String id = Util.getNextId();
        zahtev.setIdZahteva(id);
        zahtev.setStatusZahteva(TStatusZahteva.PODNET);
        zahtev.getPrijava().getBrojPrijave().setValue(BigInteger.valueOf(Long.parseLong(id)));
        id = id.concat(".xml");
        zahtev.getPrijava().getDatumPodnosenja().setValue(Util.getXMLGregorianCalendarCurrentDate());

        zahtev.getPrijava().getBrojPrijave().setProperty("pred:brojPrijave");
        zahtev.getPrijava().getBrojPrijave().setDatatype("xs:date");

        zahtev.getPrijava().getDatumPodnosenja().setProperty("pred:datumPodnosenja");
        zahtev.getPrijava().getDatumPodnosenja().setDatatype("xs:date");

        for (PodaciOAutorima.TAutor autor :  zahtev.getAutorskoDelo().getPodaciOAutoru().getAutor()) {
            autor.getLicniPodaci().getPunoIme().getIme().setProperty("pred:imeAutora");
            autor.getLicniPodaci().getPunoIme().getIme().setDatatype("xs:string");
            autor.getLicniPodaci().getPunoIme().getPrezime().setProperty("pred:prezimeAutora");
            autor.getLicniPodaci().getPunoIme().getPrezime().setDatatype("xs:string");
        }

        zahtev.getReferences().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/p1/predicate/");
        zahtev.getReferences().put(QName.valueOf("xmlns:xsi"), "http://www.w3.org/2001/XMLSchema-instance");

        TLice podnosilac = zahtev.getPodnosilac();
        if (podnosilac instanceof TFizickoLice) {
            ((TFizickoLice) podnosilac).getPunoIme().getIme().setProperty("pred:imePodnosioca");
            ((TFizickoLice) podnosilac).getPunoIme().getIme().setDatatype("xs:string");
            ((TFizickoLice) podnosilac).getPunoIme().getPrezime().setProperty("pred:prezimePodnosioca");
            ((TFizickoLice) podnosilac).getPunoIme().getPrezime().setDatatype("xs:string");
        }
        else {
            ((TPravnoLice) podnosilac).getPoslovnoIme().setProperty("pred:poslovnoImePodnosioca");
            ((TPravnoLice) podnosilac).getPoslovnoIme().setDatatype("xs:string");
        }

        OutputStream os = jaxbParser.marshall(zahtev, "./data/a-1.xsd");
        autorskaPravaRepository.save(id, os);
        //repo.save("/db/zahtevi_za_autorska_prava", id, zahtev, );

        XMLResource resource = autorskaPravaRepository.load(id);
        //byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        //fusekiWriter.saveRdf(new ByteArrayInputStream(out), "/zahtevi_za_autorska_prava");
        return zahtev;
    }

    public List<ZahtevZaAutorskaPrava> getAll() {
        return autorskaPravaRepository.getAll();
    }
}
