package rs.tim14.xml.xmldb;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.OutputKeys;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import rs.tim14.xml.util.AuthenticationUtilities;

public class ExistDbManager {

	private void openConnection() throws Exception {
		Class<?> cl = Class.forName(AuthenticationUtilities.loadProperties().driver);
		Database database = (Database) cl.getDeclaredConstructor().newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);
	}

	private void closeConnection(Collection collection, XMLResource resource) throws XMLDBException {
		if (collection != null) {
			collection.close();
		}
		if (resource != null) {
			((EXistResource) resource).freeResources();
		}
	}

	public void store(String collectionId, String documentId, String filePath) throws Exception {
		openConnection();
		Collection col = null;
		XMLResource res = null;

		try {

			col = getOrCreateCollection(collectionId);
			res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
			File f = new File(filePath);
			if(!f.canRead()) {
				return;
			}
			res.setContent(f);
			col.storeResource(res);
		}
		finally {
			closeConnection(col, res);
		}
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
	}
