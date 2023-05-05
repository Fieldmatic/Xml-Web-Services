package rs.tim14.xml.repository;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamSource;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import lombok.RequiredArgsConstructor;
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
}
