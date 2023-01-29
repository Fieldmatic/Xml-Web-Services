package rs.tim14.xml.repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.xmldb.api.modules.XMLResource;
import rs.tim14.xml.jaxb.JaxbParser;
import rs.tim14.xml.model.User;
import rs.tim14.xml.xmldb.ExistDbManager;

import java.io.OutputStream;
import java.util.Objects;
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

    public User getUserByEmail(String email) throws Exception {
        XMLResource resource = existDbManager.load("/db/users", email);
        if (Objects.isNull(resource)) return null;
        else return jaxbParser.unmarshall(existDbManager.load("/db/users", email));
    }

}
