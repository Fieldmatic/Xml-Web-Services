package rs.tim14.xml.repository;

import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;
import rs.tim14.xml.util.AuthenticationUtilities;
import rs.tim14.xml.xmldb.ExistDbManager;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

@Repository
public class PatentRepository {

    public static final String COLLECTION_ID = "/db/zahtevi_za_priznanje_patenta";

    public PatentRepository() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        ExistDbManager.init();
    }


    public void save(String documentId, OutputStream os) throws Exception {
        ExistDbManager.store(COLLECTION_ID, documentId.concat(".xml"), os.toString());
    }

    public ZahtevZaPriznanjePatenta getById(String id) throws XMLDBException {
        return ExistDbManager.getById(id);
    }

    public List<ZahtevZaPriznanjePatenta> getAll() {
        return ExistDbManager.getAll(COLLECTION_ID);
    }

    public String getPath(String id) throws Exception {
        String path = Paths.get("patent-backend/data", "xml", id + ".xml").toAbsolutePath().toString();
        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = getById(id);
        OutputStream os = JaxbParser.marshall(zahtevZaPriznanjePatenta, "./patent-backend/data/p-1.xsd");
        try (OutputStream outputStream = new FileOutputStream(path)) {
            ((ByteArrayOutputStream) os).writeTo(outputStream);
        }
        return path;
    }

    public List<ZahtevZaPriznanjePatenta> getByIds(final List<String> ids) throws XMLDBException {
        final List<ZahtevZaPriznanjePatenta> zahtevi = new ArrayList<>();
        for (final String id : ids)
            zahtevi.add(getById(id));
        return zahtevi;
    }

    public List<ZahtevZaPriznanjePatenta> dobaviPoTekstu(final List<String> filteri) throws IOException, XMLDBException {
        final AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
        Collection col = null;

        ZahtevZaPriznanjePatenta patent = null;
        List<ZahtevZaPriznanjePatenta> obrasci = null;
        try {
            col = DatabaseManager.getCollection(conn.uri + COLLECTION_ID);
            final XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");

            final String xPathExp = getXPathExp(filteri);
            final ResourceSet result = xPathQueryService.query(xPathExp);
            final ResourceIterator i = result.getIterator();
            XMLResource res = null;
            obrasci = new ArrayList<>();
            while (i.hasMoreResources()) {
                res = (XMLResource) i.nextResource();
                patent = JaxbParser.unmarshallFromDOM(res.getContentAsDOM());
                if (!obrasci.contains(patent))
                    obrasci.add(patent);
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
        return obrasci;
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

}
