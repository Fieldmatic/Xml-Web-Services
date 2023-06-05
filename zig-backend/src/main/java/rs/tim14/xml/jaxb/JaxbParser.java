package rs.tim14.xml.jaxb;

import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ResenjeZahteva;
import rs.tim14.xml.model.zahtev_za_priznanje_ziga.ZahtevZaPriznanjeZiga;
import rs.tim14.xml.util.MyValidationEventHandler;
import rs.tim14.xml.util.NSPrefixMapper;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

@Service
public class JaxbParser {
	private static JAXBContext context;

	static {
		try {
			context = JAXBContext.newInstance("rs.tim14.xml.model.zahtev_za_priznanje_ziga");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}


	private static JAXBContext context;

	static {
		try {
			context = JAXBContext.newInstance("rs.tim14.xml.model.zahtev_za_priznanje_ziga");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public ZahtevZaPriznanjeZiga unmarshall(StreamSource ss) throws JAXBException {
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(ss);
	}

	public <T> T unmarshall(String xmlPath, String jaxbContextPath, String schemaPath) throws JAXBException, SAXException {

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new File(schemaPath));

		Unmarshaller unmarshaller = context.createUnmarshaller();

		unmarshaller.setSchema(schema);
		unmarshaller.setEventHandler(new MyValidationEventHandler());

		return (T) unmarshaller.unmarshal(new File(xmlPath));
	}

	public static <T> OutputStream marshall(T objectToMarshall, String schemaPath) throws JAXBException, SAXException {
		JAXBContext context = JAXBContext.newInstance(objectToMarshall.getClass().getPackage().getName());
		Marshaller marshaller = context.createMarshaller();

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		File schemaFile = new File(schemaPath);
		Schema schema = schemaFactory.newSchema(schemaFile);
		marshaller.setSchema(schema);

		marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		OutputStream os = new ByteArrayOutputStream();
		marshaller.marshal(objectToMarshall, os);
		return os;
	}

	public static OutputStream marshall(ResenjeZahteva resenjeZahteva) throws JAXBException {
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		OutputStream os = new ByteArrayOutputStream();
		marshaller.marshal(resenjeZahteva, os);
		return os;
	}

	public static ZahtevZaPriznanjeZiga unmarshallFromDOM(Node data) throws JAXBException {
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(data);
	}

}
