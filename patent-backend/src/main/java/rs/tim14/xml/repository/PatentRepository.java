package rs.tim14.xml.repository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.zahtev_za_priznanje_patenta.ZahtevZaPriznanjePatenta;
import rs.tim14.xml.xmldb.ExistDbManager;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Repository
public class PatentRepository {

    public static final String COLLECTION_ID = "/db/zahtevi_za_priznanje_patenta";

    public PatentRepository() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        ExistDbManager.init();
    }


    public void save(String documentId, OutputStream os) throws Exception {
        ExistDbManager.store(COLLECTION_ID, documentId, os.toString());
    }

    public ZahtevZaPriznanjePatenta getById(String id) throws XMLDBException {
        return ExistDbManager.getById(id);
    }
    public List<ZahtevZaPriznanjePatenta> getAll() {
        return ExistDbManager.getAll(COLLECTION_ID);
    }

}
