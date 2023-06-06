package rs.tim14.xml.repository;

import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.autorska_prava.ResenjeZahteva;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.util.AuthenticationUtilities;

import javax.xml.bind.JAXBException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class ResenjeRepository {

    public static final String COLLECTION_RESENJE_ID = "/db/resenja_za_autorska_prava";


    private static Collection getOrCreateCollection(String collectionUri, AuthenticationUtilities.ConnectionProperties conn) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0, conn);
    }

    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset, AuthenticationUtilities.ConnectionProperties conn) throws XMLDBException {
        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);

        if (col == null) {

            if (collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String[] pathSegments = collectionUri.split("/");

            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for (int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/").append(pathSegments[i]);
                }

                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);

                if (startCol == null) {

                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset, conn);
        } else {
            return col;
        }
    }

    public static ResenjeZahteva write(ResenjeZahteva resenjeZahteva) throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException, IOException {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
        System.out.println("[INFO] Loading driver class: " + conn.driver);
        Class<?> cl = Class.forName(conn.driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        Collection col = null;
        XMLResource res;
        try {

            System.out.println("[INFO] Retrieving the collection: " + COLLECTION_RESENJE_ID);
            col = getOrCreateCollection(COLLECTION_RESENJE_ID, conn);
            String documentName = "resenjeAutorskoPravo_" + resenjeZahteva.getBrojPrijave().replace('/', '_');
            res = (XMLResource) col.createResource(documentName, XMLResource.RESOURCE_TYPE);
            res.setContent(JaxbParser.marshall(resenjeZahteva));
            System.out.println("[INFO] Storing the document: " + res.getId());
            col.storeResource(res);
            System.out.println("[INFO] Done.");
        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
        return resenjeZahteva;
    }

    public String getResenjeZahtevaPath(ResenjeZahteva resenjeZahteva) throws Exception {
        final String path = Paths.get("autorska-prava-backend/data", "xml", "resenje_" + resenjeZahteva.getBrojPrijave() + ".xml").toAbsolutePath().toString();
        final OutputStream os = JaxbParser.marshall(resenjeZahteva);
        try (final OutputStream outputStream = Files.newOutputStream(Paths.get(path))) {
            ((ByteArrayOutputStream) os).writeTo(outputStream);
        }
        return path;
    }
}
