package rs.tim14.xml.jaxb;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.util.MyValidationEventHandler;
import rs.tim14.xml.util.NSPrefixMapper;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class JaxbParser {

	public <T> T unmarshall(String xmlPath, String jaxbContextPath, String schemaPath) throws JAXBException, SAXException {
		JAXBContext context = JAXBContext.newInstance(jaxbContextPath);

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File(schemaPath));

		Unmarshaller unmarshaller = context.createUnmarshaller();

		unmarshaller.setSchema(schema);
		unmarshaller.setEventHandler(new MyValidationEventHandler());

		return (T) unmarshaller.unmarshal(new File(xmlPath));
	}

	public <T> void marshall(T objectToMarshall, String jaxbContextPath) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(jaxbContextPath);
		Marshaller marshaller = context.createMarshaller();

		marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		System.out.println("[INFO] Marshalling: ");
		marshaller.marshal(objectToMarshall, System.out);
	}

}
