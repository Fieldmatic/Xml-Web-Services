package rs.tim14.xml.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xmldb.api.modules.XMLResource;
import rs.tim14.xml.model.korisnici.TFizickoLice;
import rs.tim14.xml.model.korisnici.TLice;
import rs.tim14.xml.model.korisnici.TPravnoLice;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;
import rs.tim14.xml.rdf.FusekiWriter;
import rs.tim14.xml.rdf.MetadataExtractor;
import rs.tim14.xml.repository.Repo;
import rs.tim14.xml.util.Util;
import rs.tim14.xml.xmldb.ExistDbManager;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
@RequiredArgsConstructor
public class ZahtevZaPriznanjePatentaService {

    private final Repo repo;
    private final ExistDbManager existDbManager;
    private final MetadataExtractor metadataExtractor;
    private final FusekiWriter fusekiWriter;

    public ZahtevZaPriznanjePatenta create(ZahtevZaPriznanjePatenta zahtev) throws Exception {
        String id = Util.getNextId();
        zahtev.getPrijava().getBrojPrijave().setValue(BigInteger.valueOf(Long.parseLong(id)));
        id = id.concat(".xml");

        zahtev.getPrijava().getDatumPodnosenja().setValue(Util.getXMLGregorianCalendarCurrentDate());

        zahtev.getPrijava().getBrojPrijave().setProperty("pred:brojPrijave");
        zahtev.getPrijava().getBrojPrijave().setDatatype("xs:date");

        zahtev.getPrijava().getDatumPodnosenja().setProperty("pred:datumPodnosenja");
        zahtev.getPrijava().getDatumPodnosenja().setDatatype("xs:date");

        zahtev.getPrijava().getDatumPrijema().setProperty("pred:datumPrijema");
        zahtev.getPrijava().getDatumPrijema().setDatatype("xs:date");

        zahtev.getReferences().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/p1/predicate/");
        zahtev.getReferences().put(QName.valueOf("xmlns:xsi"), "http://www.w3.org/2001/XMLSchema-instance");

        TLice podnosilac = zahtev.getPodaciOPodnosiocu().getPodnosilac();
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


        repo.save("/db/zahtevi_za_priznanje_patenta", id, zahtev, "./data/p-1.xsd");

        XMLResource resource = existDbManager.load("/db/zahtevi_za_priznanje_patenta", id);
        byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        fusekiWriter.saveRdf(new ByteArrayInputStream(out), "/zahtevi_za_priznanje_patenta");
        return zahtev;
    }
}
