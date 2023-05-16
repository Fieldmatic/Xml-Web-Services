package rs.tim14.xml.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Collection;
import org.xmldb.api.modules.XMLResource;
import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;
import rs.tim14.xml.xmldb.ExistDbManager;

import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ZigRepository {
    private final ExistDbManager existDbManager;

    private final JaxbParser jaxbParser;
    public static final String COLLECTION_ID = "/db/zahtevi_za_priznanje_ziga";


    public void save(String documentId, OutputStream os) throws Exception {
        ExistDbManager.store(COLLECTION_ID, documentId.concat(".xml"), os.toString());
    }

    public ZahtevZaPriznanjeZiga getById(String id) throws Exception {
        XMLResource resource = existDbManager.load("/db/zahtevi_za_priznanje_ziga", id);
        return jaxbParser.unmarshall(new StreamSource(new StringReader(resource.getContent().toString())));
    }

    public List<ZahtevZaPriznanjeZiga> getAll() throws Exception {
        List<ZahtevZaPriznanjeZiga> zahteviZaPriznanjeZiga = new ArrayList<>();
        Collection collection = existDbManager.getCollection("/db/zahtevi_za_priznanje_ziga");
        if (collection != null) {
            String[] idList = collection.listResources();
            for (int i = 0; i < collection.getResourceCount(); i++) {
                ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = jaxbParser.unmarshall(new StreamSource(new StringReader(collection.getResource(idList[i]).getContent().toString())));
                zahteviZaPriznanjeZiga.add(zahtevZaPriznanjeZiga);
            }
        }
        return zahteviZaPriznanjeZiga;
    }

    public String download(String id) throws Exception {
        String path = Paths.get("data", "xml", id + ".xml").toAbsolutePath().toString();
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = getById(id);
        OutputStream os = jaxbParser.marshall(zahtevZaPriznanjeZiga, "./data/z-1.xsd");
        try (OutputStream outputStream = new FileOutputStream(path)) {
            ((ByteArrayOutputStream) os).writeTo(outputStream);
        }
        return path;
    }

    public String getPath(String id) throws Exception {
        String path = Paths.get("autorska-prava-backend/data", "xml", id + ".xml").toAbsolutePath().toString();
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = getById(id);
        OutputStream os = JaxbParser.marshall(zahtevZaPriznanjeZiga, "./zig-backend/data/z-1.xsd");
        try (OutputStream outputStream = new FileOutputStream(path)) {
            ((ByteArrayOutputStream) os).writeTo(outputStream);
        }
        return path;
    }

}
