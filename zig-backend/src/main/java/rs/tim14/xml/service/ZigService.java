package rs.tim14.xml.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import rs.tim14.xml.dto.AllResponse;
import rs.tim14.xml.dto.PrijavaResponse;
import rs.tim14.xml.dto.request.IzvestajRequest;
import rs.tim14.xml.itext.HTMLTransformer;
import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.korisnici.TFizickoLice;
import rs.tim14.xml.model.korisnici.TLice;
import rs.tim14.xml.model.korisnici.TPravnoLice;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.TPrijava;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.TStatusZahteva;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;
import rs.tim14.xml.rdf.FusekiReader;
import rs.tim14.xml.rdf.FusekiWriter;
import rs.tim14.xml.rdf.MetadataExtractor;
import rs.tim14.xml.repository.ZigRepository;
import rs.tim14.xml.util.Util;
import rs.tim14.xml.xmldb.ExistDbManager;
import rs.tim14.xml.xslfo.XSLFOTransformer;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
        TPrijava prijava = new TPrijava();
        TPrijava.BrojPrijave brojPrijave = new TPrijava.BrojPrijave();
        brojPrijave.setValue(BigInteger.valueOf(Long.parseLong(id)));
        prijava.setBrojPrijave(brojPrijave);
        TPrijava.DatumPodnosenja datumPodnosenja = new TPrijava.DatumPodnosenja();
        datumPodnosenja.setValue(currentDate);
        prijava.setDatumPodnosenja(datumPodnosenja);
        zahtev.setPrijava(prijava);

        zahtev.getPrijava().getBrojPrijave().setProperty("pred:brojPrijave");
        zahtev.getPrijava().getBrojPrijave().setDatatype("xs:string");

        zahtev.getPrijava().getDatumPodnosenja().setProperty("pred:datumPodnosenja");
        zahtev.getPrijava().getDatumPodnosenja().setDatatype("xs:string");

        zahtev.getVrstaZiga().setProperty("pred:vrstaZiga");
        zahtev.getVrstaZiga().setDatatype("xs:string");

        zahtev.getReferences().put(QName.valueOf("xmlns:pred"), "http://www.ftn.uns.ac.rs/predicate/");
        zahtev.getReferences().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");
        zahtev.setStatusZahteva(TStatusZahteva.PODNET);

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

        String link = "http://localhost:7005/api/zig/pdf?id=" + id;

        // Generate QR code from the link
        QRCode qrCode = (QRCode) QRCode.from(link)
                .to(ImageType.PNG)
                .withSize(250, 250); // You can adjust the size as needed

        ByteArrayOutputStream qrCodeOutputStream = qrCode.stream();
        byte[] qrCodeByteArray = qrCodeOutputStream.toByteArray();

        String filePath = "./zig-backend/data/result/" + id + ".png";
        try (FileOutputStream qrCodeFileOutputStream = new FileOutputStream(filePath)) {
            qrCodeFileOutputStream.write(qrCodeByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return zahtev;
    }

    public ZahtevZaPriznanjeZiga get(String id) throws Exception {
        return repo.getById(id);
    }

    public byte[] getHTML(String id) throws Exception {
        String xmlPath = repo.download(id);
        String resultPath = "./zig-backend/data/result/" + id + ".html";
        HTMLTransformer htmlTransformer = new HTMLTransformer();
        htmlTransformer.generateHTML(xmlPath, "./zig-backend/data/xsl/z1.xsl", resultPath);
        return FileUtils.readFileToByteArray(new File(resultPath));
    }

    public byte[] getPDF(String id) throws Exception {
        String xmlPath = repo.download(id);
        String resultPath = "./zig-backend/data/result/" + id + ".pdf";
        XSLFOTransformer xslfoTransformer = new XSLFOTransformer();
        xslfoTransformer.generatePDF(xmlPath, "./zig-backend/data/xsl_fo/z1_fo.xsl", resultPath);
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

    public void setObradjen(String id, boolean odbijen) throws Exception {
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = repo.getById(id);
        if (odbijen) {
            zahtevZaPriznanjeZiga.setStatusZahteva(TStatusZahteva.ODBIJEN);
        } else {
            zahtevZaPriznanjeZiga.setStatusZahteva(TStatusZahteva.PRIHVACEN);
        }
        OutputStream os = jaxbParser.marshall(zahtevZaPriznanjeZiga, "./zig-backend/data/z-1.xsd");
        repo.save(id, os);
    }

    public ByteArrayInputStream getReport(IzvestajRequest izvestajRequest) throws Exception {
        int brojZahteva = 0;
        int prihvaceniZahtevi = 0;
        int odbijeniZahtevi = 0;
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

        XMLGregorianCalendar start = datatypeFactory.newXMLGregorianCalendar(izvestajRequest.getStart());
        XMLGregorianCalendar end = datatypeFactory.newXMLGregorianCalendar(izvestajRequest.getEnd());
        List<ZahtevZaPriznanjeZiga> zigovi = repo.getAll();
        for (ZahtevZaPriznanjeZiga zahtev : zigovi) {
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
        File file = new File("./zig-backend/data/izvestaj/" + fileName);
        return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
    }

    private String createDocument(int brojZahteva, int prihvaceniZahtevi, int odbijeniZahtevi, IzvestajRequest izvestajRequest) throws FileNotFoundException, DocumentException {
        String fileName = "izvestaj.pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("./zig-backend/data/izvestaj/" + fileName));
        document.open();

        document.add(new Paragraph("                                                         Izvestaj o zahtevima za zigove"));
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
