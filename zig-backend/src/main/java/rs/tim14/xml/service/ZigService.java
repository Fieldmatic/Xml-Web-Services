package rs.tim14.xml.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import rs.tim14.xml.dto.AllResponse;
import rs.tim14.xml.dto.PrijavaResponse;
import rs.tim14.xml.itext.HTMLTransformer;
import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.korisnici.TFizickoLice;
import rs.tim14.xml.model.korisnici.TLice;
import rs.tim14.xml.model.korisnici.TPravnoLice;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.TPrijava;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;
import rs.tim14.xml.rdf.FusekiReader;
import rs.tim14.xml.rdf.FusekiWriter;
import rs.tim14.xml.rdf.MetadataExtractor;
import rs.tim14.xml.repository.ZigRepository;
import rs.tim14.xml.util.Util;
import rs.tim14.xml.xmldb.ExistDbManager;
import rs.tim14.xml.xslfo.XSLFOTransformer;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZigService {
    private final ZigRepository repo;
    private final ExistDbManager existDbManager;
    private final MetadataExtractor metadataExtractor;
    private final FusekiWriter fusekiWriter;
    private final JaxbParser jaxbParser;
    private final ZigRepository zigRepository;

    public ZahtevZaPriznanjeZiga create(ZahtevZaPriznanjeZiga zahtev) throws Exception {
        XMLGregorianCalendar currentDate = Util.getXMLGregorianCalendarCurrentDate();
        String id = Util.getNextId();

        zahtev.setAbout("http://www.ftn.uns.ac.rs/rdf/zig/" + id);
        zahtev.getPrijava().getBrojPrijave().setValue(BigInteger.valueOf(Long.parseLong(id)));
        id = id.concat("-").concat(Integer.toString(currentDate.getYear())).concat(".xml");
        zahtev.getPrijava().getDatumPodnosenja().setValue(currentDate);


        zahtev.getPrijava().getBrojPrijave().setProperty("pred:brojPrijave");
        zahtev.getPrijava().getBrojPrijave().setDatatype("xs:string");

        zahtev.getPrijava().getDatumPodnosenja().setProperty("pred:datumPodnosenja");
        zahtev.getPrijava().getDatumPodnosenja().setDatatype("xs:string");

        zahtev.getVrstaZiga().setProperty("pred:vrstaZiga");
        zahtev.getVrstaZiga().setDatatype("xs:string");

        zahtev.getReferences().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/predicate/");
        zahtev.getReferences().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");

        TLice podnosilac = zahtev.getPodnosilac();
        if (podnosilac instanceof TFizickoLice) {
            ((TFizickoLice) podnosilac).getPunoIme().getIme().setProperty("pred:imePodnosioca");
            ((TFizickoLice) podnosilac).getPunoIme().getIme().setDatatype("xs:string");
            ((TFizickoLice) podnosilac).getPunoIme().getPrezime().setProperty("pred:prezimePodnosioca");
            ((TFizickoLice) podnosilac).getPunoIme().getPrezime().setDatatype("xs:string");
        } else {
            ((TPravnoLice) podnosilac).getPoslovnoIme().setProperty("pred:poslovnoImePodnosioca");
            ((TPravnoLice) podnosilac).getPoslovnoIme().setDatatype("xs:string");
        }

        OutputStream os = jaxbParser.marshall(zahtev, "./zig-backend/data/z-1.xsd");
        repo.save(id, os);

        XMLResource resource = existDbManager.load("/db/zahtevi_za_priznanje_ziga", id);
        byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        fusekiWriter.saveRdf(new ByteArrayInputStream(out), "/zahtevi_za_priznanje_ziga");
        return zahtev;
    }

    public ZahtevZaPriznanjeZiga get(String id) throws Exception {
        return repo.get(id);
    }

    public byte[] getHTML(String id) throws Exception {
        String xmlPath = repo.download(id);
        String resultPath = "./data/result/" + id + ".html";
        HTMLTransformer htmlTransformer = new HTMLTransformer();
        htmlTransformer.generateHTML(xmlPath, "./data/xsl/z1.xsl", resultPath);
        return FileUtils.readFileToByteArray(new File(resultPath));
    }

    public byte[] getPDF(String id) throws Exception {
        String xmlPath = repo.download(id);
        String resultPath = "./data/result/" + id + ".pdf";
        XSLFOTransformer xslfoTransformer = new XSLFOTransformer();
        xslfoTransformer.generatePDF(xmlPath, "./data/xsl_fo/z1_fo.xsl", resultPath);
        return FileUtils.readFileToByteArray(new File(resultPath));
    }

    public AllResponse getAllRequests() throws Exception {
        AllResponse response = new AllResponse();
        response.setPrijave(new ArrayList<>());
        List<ZahtevZaPriznanjeZiga> zahteviZaPriznanjeZiga = repo.getAll();
        for (ZahtevZaPriznanjeZiga zahtev : zahteviZaPriznanjeZiga) {
            TPrijava prijava = zahtev.getPrijava();
            PrijavaResponse prijavaResponse = PrijavaResponse.builder()
                    .brojZahteva(prijava.getBrojPrijave().getValue() + "/" + prijava.getDatumPodnosenja().getValue().getYear())
                    .datumPodnosenja(prijava.getDatumPodnosenja())
                    .sluzbenik(prijava.getSluzbenik())
                    .podnosilac(zahtev.getPodnosilac().getIme())
                    .status(prijava.getSluzbenik() == null ? "U obradi" : (prijava.isPrihvacena() ? "Prihvacen" : "Odbijen"))
                    .build();
            response.getPrijave().add(prijavaResponse);
        }
        return response;
    }

    public AllResponse getRequestsByUser(String email) {
        return null;
    }

    public AllResponse getAllByStatus(boolean processed) {
        return null;
    }

    public String getRDF(String id) {
        try {
            String sparqlCondition = "<http://www.ftn.uns.ac.rs/rdf/zig/" + id + "> ?d ?s .";
            return FusekiReader.readMetadata(sparqlCondition, "N-TRIPLE");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getJSON(String id) {
        try {
            String sparqlCondition = "<http://www.ftn.uns.ac.rs/rdf/zig/" + id + "> ?d ?s .";
            return FusekiReader.readMetadata(sparqlCondition, "RDF/JSON");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ZahtevZaPriznanjeZiga> dobaviPoTekstu(final List<String> filteri)
        throws XMLDBException, IOException {
        return zigRepository.dobaviPoTekstu(filteri);
    }

}
