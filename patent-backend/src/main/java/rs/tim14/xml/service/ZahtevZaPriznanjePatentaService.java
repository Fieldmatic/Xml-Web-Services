package rs.tim14.xml.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import rs.tim14.xml.itext.HTMLTransformer;
import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.korisnici.TFizickoLice;
import rs.tim14.xml.model.korisnici.TLice;
import rs.tim14.xml.model.korisnici.TPravnoLice;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.TPrijava;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;
import rs.tim14.xml.rdf.FusekiReader;
import rs.tim14.xml.rdf.FusekiWriter;
import rs.tim14.xml.rdf.MetadataExtractor;
import rs.tim14.xml.repository.PatentRepository;
import rs.tim14.xml.util.Util;
import rs.tim14.xml.xmldb.ExistDbManager;
import rs.tim14.xml.xslfo.XSLFOTransformer;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ZahtevZaPriznanjePatentaService {

    private final PatentRepository patentRepository;
    private final ExistDbManager existDbManager;
    private final MetadataExtractor metadataExtractor;
    private final FusekiWriter fusekiWriter;
    private final JaxbParser jaxbParser;

    public ZahtevZaPriznanjePatenta create(ZahtevZaPriznanjePatenta zahtev) throws Exception {
        String id = Util.getNextId();
        zahtev.getPrijava().getBrojPrijave().setValue(BigInteger.valueOf(Long.parseLong(id)));
        zahtev.setAbout("http://www.ftn.uns.ac.rs/rdf/patent/" + id);
        id = id.concat(".xml");
        TPrijava.DatumPodnosenja datumPodnosenja = new TPrijava.DatumPodnosenja();
        datumPodnosenja.setValue(Util.getXMLGregorianCalendarCurrentDate());
        zahtev.getPrijava().setDatumPodnosenja(datumPodnosenja);

        zahtev.getPrijava().getBrojPrijave().setProperty("pred:brojPrijave");
        zahtev.getPrijava().getBrojPrijave().setDatatype("xs:string");

        zahtev.getPrijava().getDatumPodnosenja().setProperty("pred:datumPodnosenja");
        zahtev.getPrijava().getDatumPodnosenja().setDatatype("xs:string");

        zahtev.getReferences().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/predicate/");
        zahtev.getReferences().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");

        TLice podnosilac = zahtev.getPodaciOPodnosiocu().getPodnosilac();
        if (podnosilac instanceof TFizickoLice) {
            ((TFizickoLice) podnosilac).getPunoIme().getIme().setProperty("pred:imePodnosioca");
            ((TFizickoLice) podnosilac).getPunoIme().getIme().setDatatype("xs:string");
            ((TFizickoLice) podnosilac).getPunoIme().getPrezime().setProperty("pred:prezimePodnosioca");
            ((TFizickoLice) podnosilac).getPunoIme().getPrezime().setDatatype("xs:string");
        } else {
            ((TPravnoLice) podnosilac).getPoslovnoIme().setProperty("pred:poslovnoImePodnosioca");
            ((TPravnoLice) podnosilac).getPoslovnoIme().setDatatype("xs:string");
        }

        OutputStream os = jaxbParser.marshall(zahtev, "./patent-backend/data/p-1.xsd");
        patentRepository.save(id, os);

        XMLResource resource = existDbManager.load("/db/zahtevi_za_priznanje_patenta", id);
        byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        fusekiWriter.saveRdf(new ByteArrayInputStream(out), "/zahtevi_za_priznanje_patenta");
        return zahtev;
    }

    public ZahtevZaPriznanjePatenta getById(String id) throws XMLDBException {
        return patentRepository.getById(id);
    }

    public List<ZahtevZaPriznanjePatenta> getAll() {
        return patentRepository.getAll();
    }

    public byte[] getHTML(String id) throws Exception {
        String xmlPath = patentRepository.getPath(id);
        String resultPath = "./patent-backend/data/result/" + id + ".html";
        HTMLTransformer htmlTransformer = new HTMLTransformer();
        htmlTransformer.generateHTML(xmlPath, "./patent-backend/data/xsl/p1.xsl", resultPath);
        return FileUtils.readFileToByteArray(new File(resultPath));
    }

    public byte[] getPDF(String id) throws Exception {
        String xmlPath = patentRepository.getPath(id);
        String resultPath = "./patent-backend/data/result/" + id + ".pdf";
        XSLFOTransformer xslfoTransformer = new XSLFOTransformer();
        xslfoTransformer.generatePDF(xmlPath, "./patent-backend/data/xsl_fo/p1_fo.xsl", resultPath);
        return FileUtils.readFileToByteArray(new File(resultPath));
    }

    public String getRDF(String id) {
        try {
            String sparqlCondition = "<http://www.ftn.uns.ac.rs/rdf/patent/" + id + "> ?d ?s .";
            return FusekiReader.readMetadata(sparqlCondition, "N-TRIPLE");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getJSON(String id) {
        try {
            String sparqlCondition = "<http://www.ftn.uns.ac.rs/rdf/patent/" + id + "> ?d ?s .";
            return FusekiReader.readMetadata(sparqlCondition, "RDF/JSON");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ZahtevZaPriznanjePatenta> dobaviPoTekstu(final List<String> filteri)
        throws XMLDBException, IOException {
        return patentRepository.dobaviPoTekstu(filteri);
    }
}
