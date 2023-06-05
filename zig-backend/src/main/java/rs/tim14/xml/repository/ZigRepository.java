package rs.tim14.xml.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;
import rs.tim14.xml.util.AuthenticationUtilities;
import rs.tim14.xml.xmldb.ExistDbManager;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
        ExistDbManager.store(COLLECTION_ID, documentId, os.toString());
    }

    public ZahtevZaPriznanjeZiga get(String id) throws Exception {
        XMLResource resource = existDbManager.load("/db/zahtevi_za_priznanje_ziga", id + ".xml");
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
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = get(id);
        OutputStream os = jaxbParser.marshall(zahtevZaPriznanjeZiga, "./data/z-1.xsd");
        try (OutputStream outputStream = new FileOutputStream(path)) {
            ((ByteArrayOutputStream) os).writeTo(outputStream);
        }
        return path;
    }

    public String getPath(String id) throws Exception {
        String path = Paths.get("autorska-prava-backend/data", "xml", id + ".xml").toAbsolutePath().toString();
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = get(id);
        OutputStream os = JaxbParser.marshall(zahtevZaPriznanjeZiga, "./zig-backend/data/z-1.xsd");
        try (OutputStream outputStream = new FileOutputStream(path)) {
            ((ByteArrayOutputStream) os).writeTo(outputStream);
        }
        return path;
    }

    public List<ZahtevZaPriznanjeZiga> dobaviPoTekstu(final List<String> filteri) throws IOException, XMLDBException {
        final AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
        Collection col = null;

        ZahtevZaPriznanjeZiga zagtevZaPriznanjeZiga = null;
        List<ZahtevZaPriznanjeZiga> zahtevi = null;
        try {
            col = DatabaseManager.getCollection(conn.uri + COLLECTION_ID);
            final XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");

            final String xPathExp = getXPathExp(filteri);
            final ResourceSet result = xPathQueryService.query(xPathExp);
            final ResourceIterator i = result.getIterator();
            XMLResource res = null;
            zahtevi = new ArrayList<>();
            while (i.hasMoreResources()) {
                res = (XMLResource) i.nextResource();
                zagtevZaPriznanjeZiga = JaxbParser.unmarshallFromDOM(res.getContentAsDOM());
                if (!zahtevi.contains(zagtevZaPriznanjeZiga))
                    zahtevi.add(zagtevZaPriznanjeZiga);
            }
        } catch (final JAXBException e) {
            e.printStackTrace();
        } finally {
            if (col != null)
                try {
                    col.close();
                } catch (final XMLDBException xe) {
                    xe.printStackTrace();
                }
        }
        return zahtevi;
    }

    private static String getXPathExp(final List<String> filteri) {
        final StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("/*[");
        queryBuilder.append("contains(., '").append(filteri.get(0)).append("')");
        for (int i = 1; i < filteri.size(); i++)
            queryBuilder.append(" or contains(., '").append(filteri.get(i)).append("')");
        queryBuilder.append("]");

        return queryBuilder.toString();
    }

    public List<ZahtevZaPriznanjeZiga> getByIds(final List<String> ids) throws Exception {
        final List<ZahtevZaPriznanjeZiga> zahtevi = new ArrayList<>();
        for (final String id : ids)
            zahtevi.add(get(id));
        return zahtevi;
    }

}
