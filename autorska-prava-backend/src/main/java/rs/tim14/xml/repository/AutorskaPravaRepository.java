package rs.tim14.xml.repository;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;

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
		return ExistDbManager.load(COLLECTION_ID, documentId);
	}

	public void save(String documentId, OutputStream os) throws Exception {
		ExistDbManager.store(COLLECTION_ID, documentId, os.toString());
	}

	public ZahtevZaAutorskaPrava getById(String id) throws XMLDBException {
		return ExistDbManager.getById(id);
	}

	public String getPath(String id) throws Exception {
		String path = Paths.get("autorska-prava-backend/data", "xml", id + ".xml").toAbsolutePath().toString();
		ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = getById(id);
		OutputStream os = JaxbParser.marshall(zahtevZaAutorskaPrava, "./autorska-prava-backend/data/a-1.xsd");
		try(OutputStream outputStream = new FileOutputStream(path)) {
			((ByteArrayOutputStream)os).writeTo(outputStream);
		}
		return path;
	}
}
