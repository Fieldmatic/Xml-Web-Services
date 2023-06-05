package rs.tim14.xml.repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.util.AuthenticationUtilities;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AutorskaPravaRepository {

	public static final String COLLECTION_ID = "/db/zahtevi_za_autorska_prava";

	public AutorskaPravaRepository() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		ExistDbManager.init();
	}

	public List<ZahtevZaAutorskaPrava> getAll() {
		return ExistDbManager.getAll(COLLECTION_ID);
	}

	public XMLResource load(String documentId) throws Exception {
		return ExistDbManager.load(COLLECTION_ID, documentId.concat(".xml"));
	}

	public void save(String documentId, OutputStream os) throws Exception {
		ExistDbManager.store(COLLECTION_ID, documentId.concat(".xml"), os.toString());
	}

	public ZahtevZaAutorskaPrava getById(final String id) throws XMLDBException {
		return ExistDbManager.getById(id);
	}

	public List<ZahtevZaAutorskaPrava> getByIds(final List<String> ids) throws XMLDBException {
		final List<ZahtevZaAutorskaPrava> zahtevi = new ArrayList<>();
		for (final String id : ids)
			zahtevi.add(getById(id));
		return zahtevi;
	}

	public String getPath(final String id) throws Exception {
		final String path = Paths.get("autorska-prava-backend/data", "xml", id + ".xml").toAbsolutePath().toString();
		final ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = getById(id);
		final OutputStream os = JaxbParser.marshall(zahtevZaAutorskaPrava, "./autorska-prava-backend/data/a-1.xsd");
		try (final OutputStream outputStream = Files.newOutputStream(Paths.get(path))) {
			((ByteArrayOutputStream) os).writeTo(outputStream);
		}
		return path;
	}

	public List<ZahtevZaAutorskaPrava> dobaviPoTekstu(final List<String> filteri) throws IOException, XMLDBException {
		final AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		Collection col = null;

		ZahtevZaAutorskaPrava obrazacAutorskoDelo = null;
		List<ZahtevZaAutorskaPrava> obrasci = null;
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
				obrazacAutorskoDelo = JaxbParser.unmarshallFromDOM(res.getContentAsDOM());
				if (!obrasci.contains(obrazacAutorskoDelo))
					obrasci.add(obrazacAutorskoDelo);
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
