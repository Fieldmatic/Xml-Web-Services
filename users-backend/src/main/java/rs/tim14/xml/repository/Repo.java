package rs.tim14.xml.repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.xmldb.ExistDbManager;

import java.io.OutputStream;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class Repo {
    private final ExistDbManager existDbManager;

    private final JaxbParser jaxbParser;

    public <T> void save(String collectionId, String documentId, T objectToSave, String schemaPath) throws Exception {
       OutputStream os = jaxbParser.marshall(objectToSave, schemaPath);
       existDbManager.store(collectionId, documentId, os.toString());
    }

}
