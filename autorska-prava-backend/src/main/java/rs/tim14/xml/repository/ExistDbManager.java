package rs.tim14.xml.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.transform.OutputKeys;

import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Service;
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

import static rs.tim14.xml.repository.AutorskaPravaRepository.COLLECTION_A1_ID;
import static rs.tim14.xml.repository.ResenjeRepository.COLLECTION_RESENJE_ID;

@Service
public class ExistDbManager {

	private static AuthenticationUtilities.ConnectionProperties conn;

	public static void init() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
		conn = AuthenticationUtilities.loadProperties();
		Class<?> cl = Class.forName(conn.driver);
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);
	}

	private static void openConnection() throws Exception {
		Class<?> cl = Class.forName(AuthenticationUtilities.loadProperties().driver);
		Database database = (Database) cl.getDeclaredConstructor().newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);
	}

	private static void closeConnection(Collection collection, XMLResource resource) throws XMLDBException {
		if (collection != null) {
			collection.close();
		}
		if (resource != null) {
			((EXistResource) resource).freeResources();
		}
	}

	public static void store(String collectionId, String documentId, String xmlString) throws Exception {
		openConnection();
		Collection col = null;
		XMLResource res = null;

		try {

			col = getOrCreateCollection(collectionId);
			res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
			res.setContent(xmlString);
			col.storeResource(res);
		}
		finally {
			closeConnection(col, res);
		}
	}

	public static XMLResource load(String collectionUri, String documentId) throws Exception  {
		openConnection();
		Collection collection = null;
		XMLResource resource =  null;
		try {
			collection = DatabaseManager.getCollection(AuthenticationUtilities.loadProperties().uri + collectionUri,
				AuthenticationUtilities.loadProperties().user,
				AuthenticationUtilities.loadProperties().password);
			collection.setProperty(OutputKeys.INDENT, "yes");
			resource = (XMLResource) collection.getResource(documentId);
		} catch (Exception e) {
			closeConnection(collection, resource);
		}
		return resource;
	}

	private static Collection getOrCreateCollection(String collectionUri) throws XMLDBException, IOException {
		return getOrCreateCollection(collectionUri, 0);
	}

	private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException, IOException {

		Collection col = DatabaseManager.getCollection(AuthenticationUtilities.loadProperties().uri + collectionUri, AuthenticationUtilities.loadProperties().user, AuthenticationUtilities.loadProperties().password);

		if (col == null) {

			if (collectionUri.startsWith("/")) {
				collectionUri = collectionUri.substring(1);
			}

			String[] pathSegments = collectionUri.split("/");

			if (pathSegments.length > 0) {
				StringBuilder path = new StringBuilder();

				for(int i = 0; i <= pathSegmentOffset; i++) {
					path.append("/").append(pathSegments[i]);
				}

				Collection startCol = DatabaseManager.getCollection(AuthenticationUtilities.loadProperties().uri + path, AuthenticationUtilities.loadProperties().user, AuthenticationUtilities.loadProperties().password);

				if (startCol == null) {
					String parentPath = path.substring(0, path.lastIndexOf("/"));
					Collection parentCol = DatabaseManager.getCollection(AuthenticationUtilities.loadProperties().uri + parentPath, AuthenticationUtilities.loadProperties().user, AuthenticationUtilities.loadProperties().password);

					CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

					System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
					col = mgt.createCollection(pathSegments[pathSegmentOffset]);

					col.close();
					parentCol.close();

				} else {
					startCol.close();
				}
			}
			return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
		} else {
			return col;
		}
	}

	public static List<ZahtevZaAutorskaPrava> getAll(String collectionId) {
		Collection col = null;
		XMLResource res = null;
		try {
			List<ZahtevZaAutorskaPrava> zahtevZaDeloList = new ArrayList<>();
			col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
			col.setProperty(OutputKeys.INDENT, "yes");
			for(String s: col.listResources()){
				res = (XMLResource)col.getResource(s);
				zahtevZaDeloList.add((ZahtevZaAutorskaPrava) JaxbParser.unmarshallFromDOM(res.getContentAsDOM()));
			}

			return zahtevZaDeloList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if(res != null) {
				try {
					((EXistResource)res).freeResources();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}

			if(col != null) {
				try {
					col.close();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
	}

	public static ZahtevZaAutorskaPrava getById(String id) throws XMLDBException {

		Collection col = null;
		XMLResource res = null;
		try {
			col = DatabaseManager.getCollection(conn.uri + COLLECTION_A1_ID);
			col.setProperty(OutputKeys.INDENT, "yes");
			res = (XMLResource) col.getResource(id.concat(".xml"));

			if(res == null) {
				System.out.println("[WARNING] Document '" + id + "' can not be found!");
			} else {
				return (ZahtevZaAutorskaPrava) JaxbParser.unmarshallFromDOM(res.getContentAsDOM());
			}
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		} finally {
			if(res != null) {
				try {
					((EXistResource)res).freeResources();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
			if(col != null) {
				try {
					col.close();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
		return null;
	}
}
