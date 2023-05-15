package rs.tim14.xml.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import rs.tim14.xml.dto.requests.IzvestajRequest;
import rs.tim14.xml.itext.HTMLTransformer;
import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.autorska_prava.PodaciOAutorima;
import rs.tim14.xml.model.autorska_prava.TStatusZahteva;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.model.korisnici.TFizickoLice;
import rs.tim14.xml.model.korisnici.TLice;
import rs.tim14.xml.model.korisnici.TPravnoLice;
import rs.tim14.xml.rdf.FusekiReader;
import rs.tim14.xml.rdf.FusekiWriter;
import rs.tim14.xml.rdf.MetadataExtractor;
import rs.tim14.xml.repository.AutorskaPravaRepository;
import rs.tim14.xml.util.Util;
import rs.tim14.xml.xslfo.XSLFOTransformer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.*;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AutorskaPravaService {
    private final MetadataExtractor metadataExtractor;
    private final FusekiWriter fusekiWriter;

    private final AutorskaPravaRepository autorskaPravaRepository;

    private final JaxbParser jaxbParser;

    public ZahtevZaAutorskaPrava create(ZahtevZaAutorskaPrava zahtev) throws Exception {
        String id = Util.getNextId();
        zahtev.setIdZahteva(id);
        zahtev.setStatusZahteva(TStatusZahteva.PODNET);
        zahtev.getPrijava().getBrojPrijave().setValue(BigInteger.valueOf(Long.parseLong(id)));
        zahtev.setAbout("http://www.ftn.uns.ac.rs/rdf/a1/" + id);

        zahtev.getPrijava().getDatumPodnosenja().setValue(Util.getXMLGregorianCalendarCurrentDate());

        zahtev.getPrijava().getBrojPrijave().setProperty("pred:brojPrijave");
        zahtev.getPrijava().getBrojPrijave().setDatatype("xs:string");

        zahtev.getPrijava().getDatumPodnosenja().setProperty("pred:datumPodnosenja");
        zahtev.getPrijava().getDatumPodnosenja().setDatatype("xs:string");

        for (PodaciOAutorima.TAutor autor :  zahtev.getAutorskoDelo().getPodaciOAutoru().getAutor()) {
            autor.getLicniPodaci().getPunoIme().getIme().setProperty("pred:imeAutora");
            autor.getLicniPodaci().getPunoIme().getIme().setDatatype("xs:string");
            autor.getLicniPodaci().getPunoIme().getPrezime().setProperty("pred:prezimeAutora");
            autor.getLicniPodaci().getPunoIme().getPrezime().setDatatype("xs:string");
        }

        zahtev.getReferences().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/predicate/");
        zahtev.getReferences().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");

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

        OutputStream os = jaxbParser.marshall(zahtev, "./autorska-prava-backend/data/a-1.xsd");
        autorskaPravaRepository.save(id, os);

        XMLResource resource = autorskaPravaRepository.load(id);
        byte[] out =  metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
        fusekiWriter.saveRdf(new ByteArrayInputStream(out), "/zahtevi_za_autorska_prava");
        return zahtev;
    }

    public List<ZahtevZaAutorskaPrava> dobaviPoTekstu(List<String> filteri)
        throws XMLDBException, IOException{
        return autorskaPravaRepository.dobaviPoTekstu(filteri);
    }

    public List<ZahtevZaAutorskaPrava> getAll() {
        return autorskaPravaRepository.getAll();
    }

    public ZahtevZaAutorskaPrava getById(String id) throws XMLDBException {
        return autorskaPravaRepository.getById(id);
    }

    public byte[] getHTML(String id) throws Exception {
        String xmlPath = autorskaPravaRepository.getPath(id);
        String resultPath = "./autorska-prava-backend/data/result/" + id + ".html";
        HTMLTransformer htmlTransformer = new HTMLTransformer();
        htmlTransformer.generateHTML(xmlPath, "./autorska-prava-backend/data/xsl/a1.xsl", resultPath);
        return FileUtils.readFileToByteArray(new File(resultPath));
    }

    public byte[] getPDF(String id) throws Exception {
        String xmlPath = autorskaPravaRepository.getPath(id);
        String resultPath = "./autorska-prava-backend/data/result/" + id + ".pdf";
        XSLFOTransformer xslfoTransformer = new XSLFOTransformer();
        xslfoTransformer.generatePDF(xmlPath, "./autorska-prava-backend/data/xsl_fo/a1_fo.xsl", resultPath);
        return FileUtils.readFileToByteArray(new File(resultPath));
    }

    public String getRDF(String id) {
        try {
            String sparqlCondition = "<http://www.ftn.uns.ac.rs/rdf/a1/" + id + "> ?d ?s .";
            return FusekiReader.readMetadata(sparqlCondition, "N-TRIPLE");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getJSON(String id) {
        try {
            String sparqlCondition = "<http://www.ftn.uns.ac.rs/rdf/a1/" + id + "> ?d ?s .";
            return FusekiReader.readMetadata(sparqlCondition, "RDF/JSON");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setObradjen(String id, boolean odbijen) throws Exception {
        ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = autorskaPravaRepository.getById(id);
        if (odbijen) {
            zahtevZaAutorskaPrava.setStatusZahteva(TStatusZahteva.ODBIJEN);
        } else {
            zahtevZaAutorskaPrava.setStatusZahteva(TStatusZahteva.PRIHVACEN);
        }
        OutputStream os = jaxbParser.marshall(zahtevZaAutorskaPrava, "./autorska-prava-backend/data/a-1.xsd");
        autorskaPravaRepository.save(id, os);
    }

    public ByteArrayInputStream getReport(IzvestajRequest izvestajRequest) throws IOException, DocumentException, DatatypeConfigurationException {
        int brojZahteva = 0;
        int prihvaceniZahtevi = 0;
        int odbijeniZahtevi = 0;
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

        XMLGregorianCalendar start = datatypeFactory.newXMLGregorianCalendar(izvestajRequest.getStart());
        XMLGregorianCalendar end = datatypeFactory.newXMLGregorianCalendar(izvestajRequest.getEnd());
        List<ZahtevZaAutorskaPrava> zahtevi = autorskaPravaRepository.getAll();
        for (ZahtevZaAutorskaPrava zahtev : zahtevi) {
            TStatusZahteva statusZahteva = zahtev.getStatusZahteva();
            XMLGregorianCalendar date = zahtev.getPrijava().getDatumPodnosenja().getValue();
            int isAfter = start.compare(date);
            int isBefore = end.compare(date);
            switch (statusZahteva) {
                case ODBIJEN:
                    if ((isAfter < 0 && isBefore > 0) || isAfter == 0 || isBefore == 0) {
                        odbijeniZahtevi++;
                        brojZahteva++;
                    }
                    break;
                case PODNET:
                    if ((isAfter < 0 && isBefore > 0) || isAfter == 0 || isBefore == 0) {
                        brojZahteva++;
                    }
                    break;
                case PRIHVACEN:
                    if ((isAfter < 0 && isBefore > 0) || isAfter == 0 || isBefore == 0) {
                        prihvaceniZahtevi++;
                        brojZahteva++;
                    }
                    break;

            }
        }

        String fileName = createDocument(brojZahteva, prihvaceniZahtevi, odbijeniZahtevi, izvestajRequest);
        File file = new File("./autorska-prava-backend/data/izvestaj/" + fileName);
        return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
    }

    private String createDocument(int brojZahteva, int prihvaceniZahtevi, int odbijeniZahtevi, IzvestajRequest izvestajRequest) throws FileNotFoundException, DocumentException {
        String fileName = "izvestaj.pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("./autorska-prava-backend/data/izvestaj/" + fileName));
        document.open();

        document.add(new Paragraph("                                                         Izvestaj o zahtevima za autorska prava"));
        document.add(new Paragraph("\n               Pocetni datum: " + izvestajRequest.getStart()));
        document.add(new Paragraph("\n               Krajnji datum: " + izvestajRequest.getEnd()));
        document.add(new Paragraph("\n\n"));

        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        table.addCell(String.valueOf(brojZahteva));
        table.addCell(String.valueOf(prihvaceniZahtevi));
        table.addCell(String.valueOf(odbijeniZahtevi));
        document.add(table);
        document.close();

        return fileName;
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Broj podnetih zahteva", "Broj prihvacenih zahteva", "Broj odbijenih zahteva")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.WHITE);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
}
