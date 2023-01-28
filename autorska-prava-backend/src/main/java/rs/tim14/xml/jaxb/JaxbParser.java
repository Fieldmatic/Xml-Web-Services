package rs.tim14.xml.jaxb;
import org.springframework.stereotype.Service;
import rs.tim14.xml.model.autorska_prava.ZahtevZaAutorskaPrava;
import rs.tim14.xml.util.MyValidationEventHandler;
import rs.tim14.xml.util.NSPrefixMapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

@Service
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

	public <T> OutputStream marshall(T objectToMarshall, String schemaPath) throws JAXBException, SAXException {
		JAXBContext context = JAXBContext.newInstance(objectToMarshall.getClass().getPackage().getName());
		Marshaller marshaller = context.createMarshaller();

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		File schemaFile = new File(schemaPath);
		Schema schema = schemaFactory.newSchema(schemaFile);
		marshaller.setSchema(schema);

		marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		OutputStream os = new ByteArrayOutputStream();
		marshaller.marshal(objectToMarshall,os);
		return os;
	}

}
